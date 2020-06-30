package com.example.deliverytest.view.menuadpt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deliverytest.R
import com.example.deliverytest.model.Menu
import com.example.deliverytest.viewmodel.ListMenuViewModel

class MenuListAdapter(val viewModel: ListMenuViewModel) : RecyclerView.Adapter<MenuListItemViewHolder>() {

    var menu = emptyList<Menu>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                MenuListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_session, parent, false)
        val subView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return MenuListItemViewHolder(
            view, subView, viewModel
        )
    }

    override fun onBindViewHolder(holder: MenuListItemViewHolder, position: Int) {
        holder.bind(menu[position])
    }

    override fun getItemCount(): Int = menu.size
}