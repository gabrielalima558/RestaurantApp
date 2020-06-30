package com.example.deliverytest.view.menuadpt

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deliverytest.model.Menu
import com.example.deliverytest.view.itemsadpt.ItemsListAdapter
import com.example.deliverytest.viewmodel.ListMenuViewModel
import kotlinx.android.synthetic.main.list_session.view.*


class MenuListItemViewHolder(
    itemView: View, val itemSubView: View, val viewModel: ListMenuViewModel
) : RecyclerView.ViewHolder(itemView) {
    private lateinit var adapter: ItemsListAdapter


    fun bind(menu: Menu) {
        itemView.txt_title.text = menu.name
        adapter = ItemsListAdapter(viewModel)
        itemView.recyclerView.adapter = adapter
        itemView.recyclerView.layoutManager = LinearLayoutManager(itemSubView.context)
        adapter.items = menu.items

    }
}