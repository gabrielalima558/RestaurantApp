package com.example.deliverytest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.deliverytest.model.ItemsEntity

@Dao
interface DeliveryDao {

    @Insert
    fun saveItem(item: ItemsEntity)

    @Query("select * from ItemsEntity")
    fun listItems() : LiveData<List<ItemsEntity>>

    @Delete
    fun deleteItem(item: ItemsEntity)

    @Query("DELETE FROM ItemsEntity")
    fun deleteAllItems()

}