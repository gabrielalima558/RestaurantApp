package com.example.deliverytest.api

import com.example.deliverytest.model.Menu
import com.example.deliverytest.model.Restaurant
import io.reactivex.Observable
import retrofit2.http.GET

interface DeliveryService {
    @GET("menu")
    fun getMenuRestaurant(): Observable<List<Menu>>
    @GET("restaurant")
    fun getRestaurant(): Observable<Restaurant>
}