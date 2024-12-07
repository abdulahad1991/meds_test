package com.demo.medstest.data.repo

import com.demo.medstest.data.local.MedicineDao
import com.demo.medstest.data.model.Medicine
import com.demo.medstest.data.model.MedicineResponse
import com.demo.medstest.data.network.MedicineService
import com.demo.medstest.utils.MedicineJsonAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MedicineRepository @Inject constructor(
    private val medicineService: MedicineService,
    private val medicineDao: MedicineDao
) {
    fun getMedicines(): Flow<List<Medicine>> = flow {
        val localMedicines = medicineDao.getAllMedicines()

        if (localMedicines.isEmpty()) {
            try {
                val remoteMedicinesResponse = medicineService.getMedicines()
                val remoteMedicines: List<Medicine> =
                    MedicineJsonAdapter().fromJson(remoteMedicinesResponse)
                medicineDao.clearMedicines()
                medicineDao.insertMedicines(remoteMedicines)
                emit(remoteMedicines)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(localMedicines)
            }
        } else {
            emit(localMedicines)
        }
    }

    fun getMedicinesById(id: String): Flow<Medicine?> = flow {
        val medicine = medicineDao.getMedicineById(medicineId = id)

        if (medicine != null) {
            emit(medicine)
        } else {
            emit(null)
        }
    }
}