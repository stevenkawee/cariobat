package com.example.cariobat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

//    @Query("SELECT * FROM users WHERE username = :username AND email = :email LIMIT 1")
//    fun getCurrentUser(username: String, email: String): User

    @Query("SELECT * FROM users LIMIT 1")
    fun getCurrentUser(): User



    @Update
    suspend fun updateUser(user: User)

    @Query("UPDATE users SET saldo = :newSaldo WHERE id = :userId")
    suspend fun updateSaldo(userId: Int, newSaldo: Double)

    @Query("UPDATE users SET idOrder = :orderId WHERE id = :userId")
    suspend fun updateOrderId(userId: Int, orderId: String)

    @Query("SELECT * FROM users WHERE username = :username AND email = :email LIMIT 1")
    suspend fun getUserByUsernameAndEmail(username: String, email: String): User?

}
