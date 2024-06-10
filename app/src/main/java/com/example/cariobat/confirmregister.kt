package com.example.cariobat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class confirmregister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmregister)

        val etAllergy = findViewById<EditText>(R.id.etalergi)
        val cbPregnancyStatus = findViewById<CheckBox>(R.id.cbtidakhamil)
        val cbInternalDisease = findViewById<CheckBox>(R.id.cbriwayat)
        val etInternalDisease = findViewById<EditText>(R.id.etriwayat)
        val cbLiverKidneyIssue = findViewById<CheckBox>(R.id.cbyahatiginjal)
        val cbHerbalConsumption = findViewById<CheckBox>(R.id.cbyaherbal)
        val btnfinalregis = findViewById<Button>(R.id.btnfinalregis)

        val username = intent.getStringExtra("USERNAME") ?: ""
        val email = intent.getStringExtra("EMAIL") ?: ""
        val phoneNumber = intent.getStringExtra("PHONE") ?: ""
        val address = intent.getStringExtra("ADDRESS") ?: ""
        val age = intent.getIntExtra("AGE", 0)
        val gender = intent.getStringExtra("GENDER") ?: ""


        btnfinalregis.setOnClickListener {

            val allergy = etAllergy.text.toString().trim()
            val pregnancyStatus = if (cbPregnancyStatus.isChecked) "Ya" else "Tidak"
            val internalDisease = etInternalDisease.text.toString().trim()
            val liverKidneyIssue = if (cbLiverKidneyIssue.isChecked) "Ya" else "Tidak"
            val herbalConsumption = if (cbHerbalConsumption.isChecked) "Ya" else "Tidak"

            val user = User(
                username = username,
                email = email,
                phoneNumber = phoneNumber,
                address = address,
                age = age,
                gender = gender,
                allergies = if (allergy.isNotEmpty()) allergy else null,
                pregnancyStatus = pregnancyStatus,
                internalDisease = if (internalDisease.isNotEmpty()) internalDisease else null,
                liverKidneyIssue = liverKidneyIssue,
                herbalConsumption = herbalConsumption,
                saldo = 0.0,
                idOrder = ""
            )

            CoroutineScope(Dispatchers.IO).launch {
                val userDao = DatabaseClient.getInstance(applicationContext).userDao()
                userDao.insertUser(user)
                runOnUiThread {
                    // Menampilkan pesan toast dan mengarahkan pengguna ke MainActivity
                    Toast.makeText(applicationContext, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@confirmregister, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish() // Menutup aktivitas saat ini agar tidak bisa kembali ke halaman ini
                }
            }
        }
    }
}