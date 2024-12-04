package com.demo.medstest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.medstest.data.model.Medicine


@Dao
interface MedicineDao {
    @Query("SELECT * FROM medicines")
    suspend fun getAllMedicines(): List<Medicine>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicines(medicines: List<Medicine>)

    @Query("DELETE FROM medicines")
    suspend fun clearMedicines()
}