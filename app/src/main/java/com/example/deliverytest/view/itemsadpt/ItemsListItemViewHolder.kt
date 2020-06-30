package com.example.deliverytest.view.itemsadpt

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.deliverytest.R
import com.example.deliverytest.model.Items
import com.example.deliverytest.model.ItemsEntity
import com.example.deliverytest.view.ext.toMoneyFormatClean
import com.example.deliverytest.viewmodel.ListMenuViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*


class ItemsListItemViewHolder(
    itemView: View,
    val viewModel: ListMenuViewModel
) : RecyclerView.ViewHolder(itemView) {

    fun bind(items: Items) {

        itemView.name.text = items.name
        itemView.price.text = items.price.toString().toMoneyFormatClean()

        Picasso.get()
            .load(items.image_url)
           .error(R.drawable.ic_baseline_fastfood)
           .into(itemView.picture, object : Callback {
                override fun onSuccess() {
                    Log.e("Success", "Deu bom")
                }

                override fun onError(e: Exception?) {
                    Log.e("Fail", e?.message)
                }
            })
        itemView.add_cart.setOnClickListener {
            val deliveryEntity = ItemsEntity(items.name, items.price)
            viewModel.saveItem(deliveryEntity)
        }
        itemView.setOnClickListener {



        }
    }

}