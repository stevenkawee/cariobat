package com.example.cariobat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username AND email = :email LIMIT 1")
    suspend fun getUserByUsernameAndEmail(username: String, email: String): User?
}
