package com.example.lkti.view


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lkti.model.Data
import com.example.lkti.viewmodel.MainViewModel
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI

class DataListFrag : Fragment(), AnkoLogger {

    companion object {
        const val RECEIVER = "R"
        const val TYPE = "T"
        const val FIRST_RECEIVER = 1
        const val SECOND_RECEIVER = 2
        const val BULK = "Bulk"
        const val REALTIME = "Realtime"
    }

    private var item: MutableList<Data> = mutableListOf()
    private lateinit var mainAdapter: Adapter
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return UI {
            verticalLayout {
                recyclerView {
                    layoutManager = LinearLayoutManager(context)
                    adapter = mainAdapter
                }.lparams {
                    height = matchParent
                    width = matchParent
                }
            }
        }.view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val receiver = arguments?.getInt(RECEIVER, 0)
        mainAdapter = Adapter(receiver!!, context!!, item)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        when(arguments?.getString(TYPE)){
            REALTIME ->  mainViewModel.getRealtimeData.observe(activity!!, Observer {
                it?.let { it1 ->
                    item.clear()
                    item.addAll(it1)
                    mainAdapter.notifyDataSetChanged()
                }
            })
            else -> mainViewModel.getBulkData.observe(activity!!, Observer {
                it?.let { it1 ->
                    item.clear()
                    item.addAll(it1)
                    mainAdapter.notifyDataSetChanged()
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
