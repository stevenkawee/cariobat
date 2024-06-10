package com.example.cariobat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast


class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etUsername = findViewById<EditText>(R.id.etusername)
        val etEmail = findViewById<EditText>(R.id.etemail)
        val etPhoneNumber = findViewById<EditText>(R.id.ethanphone)
        val etAddress = findViewById<EditText>(R.id.etalamat)
        val etAge = findViewById<EditText>(R.id.etumur)
        val rgGender = findViewById<RadioGroup>(R.id.rbgender)
        val btnRegis1 = findViewById<Button>(R.id.btnregis1)

        btnRegis1.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phoneNumber = etPhoneNumber.text.toString().trim()
            val address = etAddress.text.toString().trim()
            val age = etAge.text.toString().trim()
            val selectedGenderId = rgGender.checkedRadioButtonId

            if (username.isNotEmpty() && email.isNotEmpty() && phoneNumber.isNotEmpty() &&
                address.isNotEmpty() && age.isNotEmpty() && selectedGenderId != -1) {
                val gender = findViewById<RadioButton>(selectedGenderId).text.toString()
                // Saldo default 0
                val saldo = 0.0
                // Id order awalnya null
                val idOrder: String? = null

                val intent = Intent(this, confirmregister::class.java).apply {
                    putExtra("USERNAME", username)
                    putExtra("EMAIL", email)
                    putExtra("PHONE", phoneNumber)
                    putExtra("ADDRESS", address)
                    putExtra("AGE", age.toInt())
                    putExtra("GENDER", gender)
                    putExtra("SALDO", saldo)
                    putExtra("ID_ORDER", idOrder)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Isi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}