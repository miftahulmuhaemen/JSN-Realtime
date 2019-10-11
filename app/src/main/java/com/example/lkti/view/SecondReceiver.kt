//package com.example.lkti.view
//
//
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//
//import com.example.lkti.model.Data
//import com.example.lkti.view.Adapter.Companion.SECOND
//import com.example.lkti.viewmodel.MainViewModel
//import org.jetbrains.anko.recyclerview.v7.recyclerView
//import org.jetbrains.anko.support.v4.UI
//import org.jetbrains.anko.verticalLayout
//
//
//class SecondReceiver : Fragment() {
//
//    private var item: MutableList<Data> = mutableListOf()
//    lateinit var mainViewModel: MainViewModel
//    private lateinit var mainAdapter: Adapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return UI {
//            verticalLayout {
//                recyclerView {
//                    layoutManager = LinearLayoutManager(context)
//                    adapter = mainAdapter
//                }
//            }
//        }.view
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mainAdapter = Adapter(SECOND, context!!, item)
//        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
//        mainViewModel.fetchRealtimeData.observe(activity!!, Observer {
//            it?.let { it1 ->
//                item.clear()
//                item.addAll(it1)
//                mainAdapter.notifyDataSetChanged()
//            }
//        })
//
//    }
//
//}
