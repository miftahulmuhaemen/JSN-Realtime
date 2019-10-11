package com.example.lkti.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.example.lkti.R
import com.example.lkti.R.anim.design_bottom_sheet_slide_in
import com.example.lkti.R.anim.design_bottom_sheet_slide_out
import com.example.lkti.view.DataListFrag.Companion.BULK
import com.example.lkti.view.DataListFrag.Companion.FIRST_RECEIVER
import com.example.lkti.view.DataListFrag.Companion.REALTIME
import com.example.lkti.view.DataListFrag.Companion.RECEIVER
import com.example.lkti.view.DataListFrag.Companion.SECOND_RECEIVER
import com.example.lkti.view.DataListFrag.Companion.TYPE
import com.example.lkti.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    override fun onClick(view: View?) {
        when (view) {
            transmitter -> {
                when(transmitter.text) {
                    getString(R.string.realtime) -> {
                        transmitter.text = getString(R.string.bulk)
                        swipeFragment(receiver, BULK)
                    }
                    else -> {
                        transmitter.text = getString(R.string.realtime)
                        swipeFragment(receiver, REALTIME)
                    }
                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.first_transmitter -> swipeFragment(FIRST_RECEIVER, type)
            R.id.second_transmitter -> swipeFragment(SECOND_RECEIVER, type)
        }
        return true
    }

    private var receiver: Int = FIRST_RECEIVER
    private var type: String = REALTIME
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.onStart()
        swipeFragment(FIRST_RECEIVER, REALTIME)
    }

    override fun onStart() {
        super.onStart()
        nav_view.setOnNavigationItemSelectedListener(this)
        transmitter.setOnClickListener(this)
    }

    override fun onStop() {
        super.onStop()
        Timer("getRealtimeData", false).purge()
    }

    private fun swipeFragment(name: Int, type: String) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(design_bottom_sheet_slide_in, design_bottom_sheet_slide_out)
                .replace(R.id.main_container, DataListFrag().apply {
                    arguments = Bundle().apply {
                        putInt(RECEIVER, name)
                        putString(TYPE, type)
                    }
                }, DataListFrag::class.simpleName)
                .commit()
        this.receiver = name
        this.type = type
    }
}
