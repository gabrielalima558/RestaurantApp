package com.example.deliverytest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.deliverytest.api.DeliveryApi
import com.example.deliverytest.api.InitRetrofit
import com.example.deliverytest.application.Injector
import com.example.deliverytest.data.CartRepository
import com.example.deliverytest.data.ListMenuRepository

object ViewModelFactory: ViewModelProvider.NewInstanceFactory() {

    private val retrofit = InitRetrofit.retrofit

    private val deliveryApi = DeliveryApi(retrofit)

    private val deliveryRepository =
        ListMenuRepository(deliveryApi, Injector.deliveryDao())

    private val cartRepository =
        CartRepository(deliveryApi, Injector.deliveryDao())

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return if (modelClass == ListMenuViewModel::class.java) {
            ListMenuViewModel(deliveryRepository) as T
        } else {
            CartViewModel(cartRepository) as T
        }
    }
}