package com.demo.medstest.data.repo

import com.demo.medstest.data.local.MedicineDao
import com.demo.medstest.data.model.Medicine
import com.demo.medstest.data.network.MedicineService
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
                val remoteMedicines = medicineService.getMedicines()
                medicineDao.clearMedicines()
                medicineDao.insertMedicines(remoteMedicines)
                emit(remoteMedicines)
            } catch (e: Exception) {
                emit(localMedicines)
            }
        } else {
            emit(localMedicines)
        }
    }
}