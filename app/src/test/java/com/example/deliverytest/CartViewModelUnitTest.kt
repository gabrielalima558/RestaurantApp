package com.example.deliverytest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.deliverytest.api.DeliveryApi
import com.example.deliverytest.data.CartRepository
import com.example.deliverytest.model.Restaurant
import com.example.deliverytest.viewmodel.CartViewModel
import com.google.gson.GsonBuilder
import com.nhaarman.mockitokotlin2.verify
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class CartViewModelUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private var mockWebServer: MockWebServer
    private val logging = HttpLoggingInterceptor()
    private val httpClient = OkHttpClient.Builder()
    private val gson = GsonBuilder().setLenient().create()

    init {
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        mockWebServer = MockWebServer()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/").toString())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient.build())
        .build()

    @Mock
    private lateinit var restaurantObserver: Observer<Restaurant>

    private lateinit var viewModel: CartViewModel

    @Test
    fun `when getRestaurant api call is success recive result`() {
        //Arrange
        val restaurant = Restaurant(0, "Pé de Fava", 50.0, 50)

        val resultSuccessApi = MockApiRestaurant(restaurant, retrofit)
        val resultSuccess = CartRepository(resultSuccessApi, null)
        viewModel = CartViewModel(resultSuccess)
        viewModel.restaurant().observeForever(restaurantObserver)
        //Act
        viewModel.getRestaurant()
        //Assert
        verify(restaurantObserver).onChanged(restaurant)
    }

}

class MockApiRestaurant(val result: Restaurant, retrofit: Retrofit) : DeliveryApi(retrofit) {

    override fun callRestaurant(callback: (Restaurant) -> Unit) {
        callback(result)
    }

}