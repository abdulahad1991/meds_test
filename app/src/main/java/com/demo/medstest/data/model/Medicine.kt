package com.demo.medstest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

@Entity(tableName = "medicines")
@JsonClass(generateAdapter = true)
data class Medicine(
    @PrimaryKey
    @Json(ignore = true)
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val dose: String,
    val strength: String,
    val description: String? = null
)