package com.example.lkti.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.lkti.R
import com.example.lkti.model.Data
import com.example.lkti.view.Adapter.Companion.SECOND
import com.example.lkti.viewmodel.MainViewModel
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout


class SecondReceiver : Fragment() {

    private var item: MutableList<Data> = mutableListOf()
    lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return UI {
            verticalLayout {
                recyclerView {
                    layoutManager = LinearLayoutManager(context)
                    adapter = mainAdapter
                }
            }
        }.view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainAdapter = Adapter(SECOND, context!!, item)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.allData.observe(this, Observer {
            it?.let { it1 ->
                item.clear()
                item.addAll(it1)
                mainAdapter.notifyDataSetChanged()
            }
        })

    }

}
