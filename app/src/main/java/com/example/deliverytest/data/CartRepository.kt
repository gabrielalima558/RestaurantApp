package com.example.deliverytest.data

import androidx.lifecycle.MutableLiveData
import com.example.deliverytest.api.DeliveryApi
import com.example.deliverytest.db.DeliveryDao
import com.example.deliverytest.model.ItemsEntity
import com.example.deliverytest.model.Restaurant

class CartRepository(private val api: DeliveryApi, private val deliveryDao: DeliveryDao?) {

    val restaurant = MutableLiveData<Restaurant>()

    fun getRestaurant() {
        api.callRestaurant { restaurantRes ->
            restaurant.postValue(restaurantRes) }
    }

    fun getItemsDb() = deliveryDao!!.listItems()

    fun deleteItemsDb(itemsEntity: ItemsEntity) = deliveryDao!!.deleteItem(itemsEntity)
}