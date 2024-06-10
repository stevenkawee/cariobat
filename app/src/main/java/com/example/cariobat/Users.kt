package com.example.cariobat

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var username: String,
    var email: String,
    var phoneNumber: String,
    var address: String,
    var age: Int,
    val gender: String,
    val allergies: String?,
    val pregnancyStatus: String?,
    val internalDisease: String?,
    val liverKidneyIssue: String?,
    val herbalConsumption: String?,
    val saldo: Double = 0.0,
    val idOrder: String? = null

)
