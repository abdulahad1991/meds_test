package com.demo.medstest

import androidx.test.ext.junit.runners.AndroidJUnit4

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.medstest.data.model.Medicine
import com.demo.medstest.data.repo.MedicineRepository
import com.demo.medstest.viewmodel.LoginViewModel
import com.demo.medstest.viewmodel.MedicineViewModel
import com.google.ar.core.Config
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test
import org.mockito.Mock

import org.junit.Assert.*
import org.junit.Before
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.Calendar

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
@HiltAndroidTest
class AppUnitTests {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)
    @Mock
    private lateinit var medicineRepository: MedicineRepository

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var medicineViewModel: MedicineViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        HiltAndroidRule(this).inject()
        loginViewModel = LoginViewModel()
        medicineViewModel = MedicineViewModel(medicineRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test login sets username and isLoggedIn`() = runTest {
        val testUsername = "testUser"
        loginViewModel.login(testUsername)

        assertEquals(testUsername, loginViewModel.username)
        assertTrue(loginViewModel.isLoggedIn.value)
    }


    @Test
    fun `test getGreeting returns correct greeting`() {
        Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 10)
        }
        loginViewModel = LoginViewModel()
        val greeting = loginViewModel.getGreeting()
        assertEquals("Good Morning", greeting)
    }

    @Test
    fun `test fetchMedicines populates medicines list`() = runTest {
        val mockMedicines = listOf(
            Medicine(
                id = "1",
                name = "Test Medicine",
                dose = "10mg",
                strength = "500mg"
            )
        )

        `when`(medicineRepository.getMedicines()).thenReturn(flowOf(mockMedicines))

        val medicines = medicineViewModel.medicines.value

        assertEquals(1, medicines.size)
        assertEquals("Test Medicine", medicines[0].name)
    }

    @Test
    fun `test getMedicineById sets selected medicine`() = runTest {
        val mockMedicine = Medicine(
            id = "1",
            name = "Test Medicine",
            dose = "10mg",
            strength = "500mg"
        )

        `when`(medicineRepository.getMedicinesById("1")).thenReturn(flowOf(mockMedicine))

        medicineViewModel.getMedicineById("1")

        val selectedMedicine = medicineViewModel.selectedMedicine
        assertNotNull(selectedMedicine)
        assertEquals("1", selectedMedicine.value?.id)
    }
}