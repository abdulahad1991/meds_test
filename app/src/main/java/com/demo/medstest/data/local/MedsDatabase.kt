package com.demo.medstest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.medstest.data.model.MedicineEntity

@Database(entities = [MedicineEntity::class], version = 1)
abstract class MedsDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}