package com.demo.medstest.data.network

import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    fun createMedicineService(): MedicineService {
        return retrofit2.Retrofit.Builder()
            .baseUrl("https://6750005d69dc1669ec193e2a.mockapi.io/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MedicineService::class.java)
    }
}