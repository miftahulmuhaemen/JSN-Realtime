package com.example.lkti.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lkti.R
import com.example.lkti.model.Data
import com.example.lkti.view.Adapter.Companion.FIRST
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoLogger
import kotlinx.android.synthetic.main.item_list.*
import org.jetbrains.anko.info

class Adapter(
    private val type: Int,
    private val context: Context,
    private val item: List<Data>
) :
    RecyclerView.Adapter<TeamViewGridHolder>() {

    companion object {
        const val FIRST = 1
        const val SECOND = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (type == FIRST)
            FIRST
        else
            SECOND
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewGridHolder {
            return TeamViewGridHolder(LayoutInflater.from(context).inflate(R.layout.items_list, parent, false), context)
    }

    override fun onBindViewHolder(gridHolder: TeamViewGridHolder, position: Int) {
        gridHolder.bindItem(gridHolder.itemViewType, item[position])
    }

    override fun getItemCount(): Int = item.size
}

class TeamViewGridHolder(override val containerView: View, private val context: Context) :
    RecyclerView.ViewHolder(containerView), AnkoLogger,
    LayoutContainer {

    fun bindItem(type: Int, item: Data) {

        waktu.text = item.Waktu

        if(type == FIRST){
            temp_atas.text = item.T_atas_1.toString()
            temp_bawah.text = item.T_bawah_1.toString()
            relative_humidity.text = item.RH_bawah_1.toString()

            if(item.Api_1.equals("True", true))
                api.setImageResource(R.drawable.ic_check_box_black_24dp)
            else
                api.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp)

            if(item.Asap_1.equals("True", true))
                asap.setImageResource(R.drawable.ic_check_box_black_24dp)
            else
                asap.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp)

        } else {
            temp_atas.text = item.T_atas_2.toString()
            temp_bawah.text = item.T_bawah_2.toString()
            relative_humidity.text = item.RH_bawah_2.toString()

            if(item.Api_2.equals("True", true))
                api.setImageResource(R.drawable.ic_check_box_black_24dp)
            else
                api.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp)

            if(item.Asap_2.equals("True", true))
                asap.setImageResource(R.drawable.ic_check_box_black_24dp)
            else
                asap.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp)
        }
    }

}