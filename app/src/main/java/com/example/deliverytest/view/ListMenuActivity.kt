package com.example.deliverytest.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliverytest.R
import com.example.deliverytest.view.menuadpt.MenuListAdapter
import com.example.deliverytest.viewmodel.ListMenuViewModel
import com.example.deliverytest.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recyclerView

class ListMenuActivity : AppCompatActivity() {
    private val viewModel: ListMenuViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory).get(ListMenuViewModel::class.java)
    }
    private lateinit var adapter: MenuListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        adapter = MenuListAdapter(viewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.getMenu()
        viewModel.menu().observe(this, Observer {
            adapter.menu = it

        })
        viewModel.listItems().observe(this, Observer {
            if(it.isNotEmpty()){
                btn_items.visibility = View.VISIBLE
                btn_items.text = "Itens " + "(" + it.size + ")"
            }

        })
        btn_items.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)

        }
    }
}