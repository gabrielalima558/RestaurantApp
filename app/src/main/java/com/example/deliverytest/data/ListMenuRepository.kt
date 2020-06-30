package com.example.deliverytest.data

import androidx.lifecycle.MutableLiveData
import com.example.deliverytest.api.DeliveryApi
import com.example.deliverytest.db.DeliveryDao
import com.example.deliverytest.model.ItemsEntity
import com.example.deliverytest.model.Menu

class ListMenuRepository(private val api: DeliveryApi, private val deliveryDao: DeliveryDao) {

    val menu = MutableLiveData<List<Menu>>()

    fun getMenu() {
        api.callMenu { menuRes ->
            menu.postValue(menuRes)
        }
    }


    fun setItemsDb(itemsEntity: ItemsEntity) = deliveryDao.saveItem(itemsEntity)

    fun getItemsDb() = deliveryDao.listItems()


}