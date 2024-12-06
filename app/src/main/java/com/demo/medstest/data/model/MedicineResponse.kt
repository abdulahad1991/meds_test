package com.demo.medstest.data.model

import com.google.gson.annotations.SerializedName

class MedicineResponse : ArrayList<MedicineResponseItem>()

data class MedicineResponseItem(
    val problems: List<Problem>
)

data class Problem(
    val Asthma: List<Asthma>,
    val Diabetes: List<Diabete>
)

class Asthma

data class Diabete(
    val labs: List<Lab>,
    val medications: List<Medication>
)

data class Lab(
    val missing_field: String
)

data class Medication(
    val medicationsClasses: List<MedicationsClasses>
)

data class MedicationsClasses(
    val className: List<ClassName>,
    val className2: List<ClassName>
)

data class ClassName(
    val associatedDrug: List<AssociatedDrug>,
    @SerializedName("associatedDrug#2")
    val associatedDrug2: List<AssociatedDrug>
)

data class AssociatedDrug(
    val dose: String,
    val name: String,
    val strength: String
)