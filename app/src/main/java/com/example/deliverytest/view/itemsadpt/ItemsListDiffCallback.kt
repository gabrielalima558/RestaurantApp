package com.example.deliverytest.view.itemsadpt

import androidx.recyclerview.widget.DiffUtil
import com.example.deliverytest.model.Items

class ItemsListDiffCallback(
    private val oldList: List<Items>,
    private val newList: List<Items>
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