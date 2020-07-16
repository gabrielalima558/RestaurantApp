package com.example.deliverytest.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deliverytest.R
import com.example.deliverytest.view.ext.toMoneyFormatClean
import com.example.deliverytest.view.itemscartadpt.ItemsCartListAdapter
import com.example.deliverytest.viewmodel.CartViewModel
import com.example.deliverytest.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    private val viewModel: CartViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory).get(CartViewModel::class.java)
    }
    private lateinit var adapter: ItemsCartListAdapter

    var sum: Double = 0.0
    var result: Double = 0.0
    var limitPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        adapter = ItemsCartListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.listItems().observe(this, Observer {
            adapter.items = it
        })
        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    viewModel.deleteItem(adapter.getItemAt(viewHolder.adapterPosition))
                    viewModel.listItems()

                }

            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        viewModel.getRestaurant()
        viewModel.restaurant().observe(this, Observer {
            limitPrice = it.minimum_order_price
        })
        viewModel.listItems().observe(this, Observer {
            it.forEach {
                sum += it.price
                result = sum
            }
            if (result.toInt() > limitPrice && limitPrice > 0) {
                Toast.makeText(
                    this@CartActivity,
                    "Ops! O limite para a compra daterminado pelo restaurante é de " + limitPrice,
                    Toast.LENGTH_LONG
                ).show()
                sum = 0.0
                txt_total.text = result.toString().toMoneyFormatClean()
            }else{
                sum = 0.0
                txt_total.text = result.toString().toMoneyFormatClean()
            }

        })

        btn_finish.setOnClickListener {

        }

    }
}