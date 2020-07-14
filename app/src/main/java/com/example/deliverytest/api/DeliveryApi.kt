package com.example.deliverytest.api

import android.util.Log
import com.example.deliverytest.model.Menu
import com.example.deliverytest.model.Restaurant
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

open class DeliveryApi(retrofit: Retrofit) {
    private val service: DeliveryService by lazy {
        retrofit.create(DeliveryService::class.java)
    }

    open fun callMenu(callback: (List<Menu>) -> Unit) {
        val observable = service.getMenuRestaurant()
        observable
            .flatMap { results -> Observable.fromArray(results) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response?.let(callback)

            }, { error ->
                Log.e("Fail", error.message.toString())
            }
            )
    }

    fun callRestaurant(callback: (Restaurant) -> Unit){
        val observable = service.getRestaurant()
        observable
            .flatMap { results -> Observable.fromArray(results) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response?.let(callback)

            }, { error ->
                Log.e("Fail", error.message.toString())
            }
            )
    }
}