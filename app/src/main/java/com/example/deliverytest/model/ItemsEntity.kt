package com.example.deliverytest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemsEntity(val name: String,
                       val price: Double,
                       @PrimaryKey(autoGenerate = true) val id: Int = 0
)