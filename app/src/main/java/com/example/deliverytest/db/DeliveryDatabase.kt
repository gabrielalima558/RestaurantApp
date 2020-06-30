package com.example.deliverytest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deliverytest.model.ItemsEntity

@Database(entities = [ItemsEntity::class], version = 3)
abstract class DeliveryDatabase: RoomDatabase() {

    abstract fun deliveryDao(): DeliveryDao

    companion object {
        private var database : DeliveryDatabase? = null

        private val DATABASE = "DeliveryDB"

        fun getInstance(context: Context): DeliveryDatabase {
            return database ?: createDB(context).also{
                database = it
            }
        }

        private fun createDB(context: Context): DeliveryDatabase {
            return Room.databaseBuilder(context, DeliveryDatabase::class.java, DATABASE)
                .allowMainThreadQueries()
                .build()
        }
    }
}