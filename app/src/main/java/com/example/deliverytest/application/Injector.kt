package com.example.deliverytest.application

import com.example.deliverytest.db.DeliveryDatabase


object Injector {

    private val context = DeliveryApplication.getInstance()

    private val database =
        DeliveryDatabase.getInstance(
            context
        )

    fun deliveryDao() = database.deliveryDao()


}