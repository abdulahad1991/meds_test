package com.demo.medstest.data.network

import com.demo.medstest.data.model.Medicine

import retrofit2.http.GET

interface MedicineService {
    @GET("medicines")
    suspend fun getMedicines(): List<Medicine>
}