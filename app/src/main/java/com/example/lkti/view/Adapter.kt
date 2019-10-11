package com.example.lkti.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lkti.R
import com.example.lkti.model.Data
import com.example.lkti.view.DataListFrag.Companion.FIRST_RECEIVER
import com.example.lkti.view.DataListFrag.Companion.SECOND_RECEIVER
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.items_list.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class Adapter(
        private val type: Int,
        private val context: Context,
        private val item: List<Data>
) :
        RecyclerView.Adapter<TeamViewGridHolder>(), AnkoLogger {

    override fun getItemViewType(position: Int): Int {
        return if (type == FIRST_RECEIVER)
            FIRST_RECEIVER
        else
            SECOND_RECEIVER
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

        if (type == FIRST_RECEIVER) {
            temp_atas.text = item.T_atas_1.toString()
            temp_bawah.text = item.T_bawah_1.toString()
            relative_humidity.text = item.RH_bawah_1.toString()
            asap.text = item.Asap_1.toString()
            if (item.Api_1 == 1)
                api.setImageResource(R.drawable.ic_check_box_black_24dp)
            else
                api.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp)

        } else {
            temp_atas.text = item.T_atas_2.toString()
            temp_bawah.text = item.T_bawah_2.toString()
            relative_humidity.text = item.RH_bawah_2.toString()
            asap.text = item.Asap_2.toString()
            if (item.Api_2 == 1)
                api.setImageResource(R.drawable.ic_check_box_black_24dp)
            else
                api.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp)
        }
    }

}