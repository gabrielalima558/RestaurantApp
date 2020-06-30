package com.example.deliverytest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.deliverytest.data.CartRepository
import com.example.deliverytest.model.ItemsEntity

class CartViewModel(private val repository: CartRepository) : ViewModel() {
    fun restaurant() = repository.restaurant

    fun getRestaurant() = repository.getRestaurant()

    fun listItems() = repository.getItemsDb()
    fun deleteItem(itemsEntity: ItemsEntity) = repository.deleteItemsDb(itemsEntity)

}