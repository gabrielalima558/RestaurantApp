package com.example.deliverytest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.deliverytest.api.DeliveryApi
import com.example.deliverytest.api.DeliveryService
import com.example.deliverytest.application.Injector
import com.example.deliverytest.data.ListMenuRepository
import com.example.deliverytest.model.Items
import com.example.deliverytest.model.Menu
import com.example.deliverytest.viewmodel.ListMenuViewModel
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

//Para inicializar o mockito
@RunWith(MockitoJUnitRunner::class)
class ListMenuViewModelUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    //cria o mock do retrofit para a DeliveryApi
    private var mockWebServer: MockWebServer
    private val logging = HttpLoggingInterceptor()
    private val httpClient = OkHttpClient.Builder()
    private val gson = GsonBuilder().setLenient().create()

    init {
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        mockWebServer = MockWebServer()
    }
    val retrofit = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/").toString())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient.build())
        .build()

    @Mock
    private lateinit var menuObserver: Observer<List<Menu>>

    private lateinit var viewModel: ListMenuViewModel

    @Test
    fun `when getMenu api call is success recive result`() {
        //Arrange
        val menuList = listOf(
            Menu(
                2,
                "Lanches",
                listOf(Items(2, "X-Tudo", "Lanche grande", 10.0, "IMAGE"))
            )
        )

        val resultSuccessApi = MockApi(menuList, retrofit)
        val resultSuccess = ListMenuRepository(resultSuccessApi,null)
        viewModel = ListMenuViewModel(resultSuccess)
        viewModel.menu().observeForever(menuObserver)
        //Act
        viewModel.getMenu()
        //Assert
        verify(menuObserver).onChanged(menuList)
    }
}

class MockApi(val result: List<Menu>, retrofit: Retrofit) : DeliveryApi(retrofit) {
    override fun callMenu(callback: (result: List<Menu>) -> Unit) {
        callback(result)
    }
}

