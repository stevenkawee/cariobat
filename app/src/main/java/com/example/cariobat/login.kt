package com.example.cariobat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class login : AppCompatActivity() {

    private lateinit var btnloginuser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val etUsername = findViewById<EditText>(R.id.etloginusername)
        val etEmail = findViewById<EditText>(R.id.etloginemail)
        val btnloginuser = findViewById<Button>(R.id.btnloginuser)


        btnloginuser.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (username.isNotEmpty() && email.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    val userDao = DatabaseClient.getInstance(applicationContext).userDao()
                    val user = userDao.getUserByUsernameAndEmail(username, email)
                    withContext(Dispatchers.Main) {
                        if (user != null) {
                            // Login berhasil, arahkan ke HomePageActivity dengan nama user
                            Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@login, home_page::class.java)
                            intent.putExtra("USERNAME", user.username)
                            intent.putExtra("SALDO", user.saldo)
                            intent.putExtra("ID_ORDER", user.idOrder)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            finish() // Menutup LoginActivity
                        } else {
                            Toast.makeText(applicationContext, "User tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(applicationContext, "Isi semua field", Toast.LENGTH_SHORT).show()
            }
        }

    }
}