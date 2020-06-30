package com.example.deliverytest.view.menuadpt

import androidx.recyclerview.widget.DiffUtil
import com.example.deliverytest.model.Menu

class MenuListDiffCallback(
    private val oldList: List<Menu>,
    private val newList: List<Menu>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id== newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}