package com.example.cariobat

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val age: Int,
    val gender: String,
    val allergies: String?,
    val pregnancyStatus: String?,
    val internalDisease: String?,
    val liverKidneyIssue: String?,
    val herbalConsumption: String?
)
