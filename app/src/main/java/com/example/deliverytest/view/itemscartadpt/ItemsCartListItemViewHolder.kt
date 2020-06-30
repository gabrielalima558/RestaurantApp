package com.example.deliverytest.view.itemscartadpt

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.deliverytest.model.ItemsEntity
import com.example.deliverytest.view.ext.toMoneyFormatClean
import kotlinx.android.synthetic.main.list_item.view.name
import kotlinx.android.synthetic.main.list_item.view.price


class ItemsCartListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView){

    fun bind(items: ItemsEntity) {

        itemView.name.text = items.name
        itemView.price.text = items.price.toString().toMoneyFormatClean()
    }

}