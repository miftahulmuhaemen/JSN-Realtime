package com.example.lkti.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lkti.model.Data
import com.example.lkti.view.Adapter.Companion.FIRST
import com.example.lkti.viewmodel.MainViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onTouch
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class FirstReceiver : Fragment(), AnkoLogger {

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
                    isLayoutFrozen = true
                    layoutManager = LinearLayoutManager(context)
                    adapter = mainAdapter
                    onTouchEvent(null)
                }
            }
        }.view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainAdapter = Adapter(FIRST, context!!, item)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.allData.observe(this, Observer {
            it?.let { it1 ->
                item.clear()
                item.addAll(it1)
                mainAdapter.notifyDataSetChanged()
                info(mainAdapter.itemCount)
            }
        })
    }

}
