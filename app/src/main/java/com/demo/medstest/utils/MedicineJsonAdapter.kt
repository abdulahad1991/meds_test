package com.demo.medstest.utils

import com.demo.medstest.data.model.Medicine
import com.demo.medstest.data.model.MedicineResponse
import com.squareup.moshi.FromJson

class MedicineJsonAdapter {
    @FromJson
    fun fromJson(response: MedicineResponse): List<Medicine> {
        val medicines = mutableListOf<Medicine>()
        response.forEach { item ->
            item.problems.forEach { problem ->
                problem.Diabetes.forEach { diabetes ->
                    diabetes.medications.forEach { medication ->
                        medication.medicationsClasses.forEach { medicationClass ->
                            medicationClass.className.forEach { className ->
                                medicines.addAll(className.associatedDrug.map { drug ->
                                    Medicine(
                                        name = drug.name,
                                        dose = drug.dose,
                                        strength = drug.strength
                                    )
                                })
                                medicines.addAll(className.associatedDrug2.map { drug ->
                                    Medicine(
                                        name = drug.name,
                                        dose = drug.dose,
                                        strength = drug.strength
                                    )
                                })
                            }
                        }
                    }
                }
            }
        }
        return medicines
    }
}