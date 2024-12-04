package com.demo.medstest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "medicines")
@JsonClass(generateAdapter = true)
data class Medicine(
    @PrimaryKey val id: String,
    val name: String,
    val dose: String,
    val strength: String,
    val description: String? = null
)