package com.demo.medstest.data.network

import com.demo.medstest.data.model.Medicine
import com.demo.medstest.data.model.MedicineResponse

import retrofit2.http.GET

interface MedicineService {
    @GET("medicines")
    suspend fun getMedicines(): MedicineResponse
}