package com.test.plantix.ui.namelist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.test.plantix.ui.namelist.model.NameUIModel

class NameDiffCallBack : DiffUtil.ItemCallback<NameUIModel>() {
    override fun areItemsTheSame(oldItem: NameUIModel, newItem: NameUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NameUIModel, newItem: NameUIModel): Boolean {
        return oldItem.value == newItem.value
    }
}