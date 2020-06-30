package com.example.deliverytest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.deliverytest.data.ListMenuRepository
import com.example.deliverytest.model.ItemsEntity

class ListMenuViewModel(private val repository: ListMenuRepository): ViewModel() {
    fun menu() = repository.menu

    fun getMenu() = repository.getMenu()

    fun saveItem(itemsEntity: ItemsEntity) = repository.setItemsDb(itemsEntity)
    fun listItems() = repository.getItemsDb()
}