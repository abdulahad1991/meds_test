package com.demo.medstest.di

import android.content.Context
import androidx.room.Room
import com.demo.medstest.data.local.MedicineDao
import com.demo.medstest.data.local.MedsDatabase
import com.demo.medstest.data.network.MedicineService
import com.demo.medstest.data.network.RetrofitClient
import com.demo.medstest.data.repo.MedicineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMedicineService(): MedicineService = RetrofitClient.createMedicineService()

    @Provides
    @Singleton
    fun provideMedicineDatabase(@ApplicationContext context: Context): MedsDatabase {
        return Room.databaseBuilder(
            context,
            MedsDatabase::class.java,
            "medicine_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMedicineDao(database: MedsDatabase) = database.medicineDao()

    @Provides
    @Singleton
    fun provideMedicineRepository(
        medicineService: MedicineService,
        medicineDao: MedicineDao
    ) = MedicineRepository(medicineService, medicineDao)
}