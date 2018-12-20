package com.hello.homecreditchallenge.libs.rest

import com.hello.homecreditchallenge.model.WeatherData
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val ENDPOINT = "http://api.openweathermap.org"
    private const val APP_ID = "4dd9f5cfb2b4db77be919ca7e7007163"
    private const val METRIC_UNITS = "metric"

    private val retrofit = Retrofit.Builder()
        .baseUrl(ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val service: ApiService by lazy {
        retrofit.create<ApiService>(ApiService::class.java)
    }

    fun getWeatherData(vararg countryId: Int): Single<List<WeatherData>> = service
        .getWeatherData(countryId.joinToString(separator = ","), METRIC_UNITS, APP_ID)
        .map { it.weatherList }

}