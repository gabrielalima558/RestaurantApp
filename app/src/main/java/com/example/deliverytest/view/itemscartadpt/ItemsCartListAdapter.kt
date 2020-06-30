package com.example.deliverytest.view.itemscartadpt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deliverytest.R
import com.example.deliverytest.model.ItemsEntity

class ItemsCartListAdapter() :
    RecyclerView.Adapter<ItemsCartListItemViewHolder>() {

    var items = emptyList<ItemsEntity>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                ItemsCartListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsCartListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_cart, parent, false)

        return ItemsCartListItemViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ItemsCartListItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun getItemAt(position: Int): ItemsEntity{
        return items[position]
    }

    override fun getItemCount(): Int = items.size
}