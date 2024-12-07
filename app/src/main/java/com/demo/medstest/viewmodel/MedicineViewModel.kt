package com.demo.medstest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.medstest.data.model.Medicine
import com.demo.medstest.data.repo.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val repository: MedicineRepository
) : ViewModel() {
    private val _medicines = MutableStateFlow<List<Medicine>>(emptyList())
    val medicines: StateFlow<List<Medicine>> = _medicines.asStateFlow()

    private val _selectedMedicine = MutableStateFlow<Medicine?>(null)
    val selectedMedicine: StateFlow<Medicine?> = _selectedMedicine.asStateFlow()

    init {
        fetchMedicines()
    }

    private fun fetchMedicines() {
        viewModelScope.launch {
            repository.getMedicines().collect { medicineList ->
                _medicines.value = medicineList
            }
        }
    }

    fun getMedicineById(id: String?) {
        viewModelScope.launch {
            repository.getMedicinesById(id.toString()).collect { medicine ->
                _selectedMedicine.value = medicine
            }
        }
    }
}