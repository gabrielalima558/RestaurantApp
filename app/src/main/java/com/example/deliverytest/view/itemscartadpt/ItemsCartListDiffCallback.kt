package com.example.deliverytest.view.itemscartadpt

import androidx.recyclerview.widget.DiffUtil
import com.example.deliverytest.model.ItemsEntity

class ItemsCartListDiffCallback(
    private val oldList: List<ItemsEntity>,
    private val newList: List<ItemsEntity>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
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