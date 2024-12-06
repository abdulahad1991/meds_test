package com.demo.medstest.data.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    fun createMedicineService(): MedicineService {
        return retrofit2.Retrofit.Builder()
            .baseUrl("https://6750005d69dc1669ec193e2a.mockapi.io/api/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(configuredGson))
            .build()
            .create(MedicineService::class.java)
    }

    private val configuredGson: Gson
        get() = GsonBuilder()
            .setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()
}