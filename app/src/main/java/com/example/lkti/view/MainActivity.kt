package com.example.lkti.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.lkti.R
import com.example.lkti.R.anim.design_bottom_sheet_slide_in
import com.example.lkti.R.anim.design_bottom_sheet_slide_out
import com.example.lkti.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var mainViewModel: MainViewModel

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.first_transmitter -> swipeFragment(FirstReceiver())
            R.id.second_transmitter -> swipeFragment(SecondReceiver())
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.allData
        swipeFragment(FirstReceiver())
        nav_view.setOnNavigationItemSelectedListener(this)
    }

    private fun swipeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(design_bottom_sheet_slide_in, design_bottom_sheet_slide_out)
            .replace(R.id.main_container, fragment, fragment.tag)
            .commit()
    }
}
