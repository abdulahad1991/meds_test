package com.demo.medstest.data.network

import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    fun createMedicineService(): MedicineService {
        return retrofit2.Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MedicineService::class.java)
    }
}