package com.test.plantix.ui.namelist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.plantix.R
import com.test.plantix.ui.namelist.model.NameUIModel
import org.w3c.dom.Text

class NamesAdapter(private val list: List<NameUIModel>) :
    RecyclerView.Adapter<NamesAdapter.ViewHolder>() {

    private val differ: AsyncListDiffer<NameUIModel> =
        object : AsyncListDiffer<NameUIModel>(this, NameDiffCallBack()) {}


    fun setData(list: List<NameUIModel>) {
        differ.submitList(list)

    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(nameUIModel: NameUIModel) {
            itemView.findViewById<TextView>(R.id.tvName).text = nameUIModel.value
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_name, parent, false)
            .let {
                ViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}