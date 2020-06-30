package com.example.deliverytest.view.itemsadpt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deliverytest.R
import com.example.deliverytest.model.Items
import com.example.deliverytest.viewmodel.ListMenuViewModel

class ItemsListAdapter(val viewModel: ListMenuViewModel) : RecyclerView.Adapter<ItemsListItemViewHolder>() {

    var items = emptyList<Items>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                ItemsListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemsListItemViewHolder(
            view, viewModel
        )
    }

    override fun onBindViewHolder(holder: ItemsListItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}