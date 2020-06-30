package com.example.deliverytest.application

import android.app.Application

class DeliveryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: DeliveryApplication

        fun getInstance() =
            instance
    }
}