package com.example.cariobat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Toast

class profile : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etAddress: EditText
    private lateinit var etAge: EditText
    private lateinit var btnUpdateProfile: Button
    private lateinit var btnBack: Button
    private lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        etUsername = findViewById(R.id.etuser1)
        etEmail = findViewById(R.id.etemail1)
        etPhone = findViewById(R.id.ethp1)
        etAddress = findViewById(R.id.etalamat1)
        etAge = findViewById(R.id.etumur1)
        btnUpdateProfile = findViewById(R.id.btnupdateprofile)
        btnBack = findViewById(R.id.btnkembali1)
        userDao = DatabaseClient.getInstance(applicationContext).userDao()

        CoroutineScope(Dispatchers.Main).launch {
            val user = withContext(Dispatchers.IO) {
                userDao.getCurrentUser()
            }
            if (user != null) {
                updateUI(user)
            } else {

                Toast.makeText(applicationContext, "User data not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun updateUI(user: User) {
        etUsername.setText(user.username)
        etEmail.setText(user.email)
        etPhone.setText(user.phoneNumber)
        etAddress.setText(user.address)
        etAge.setText(user.age.toString())

        btnBack.setOnClickListener {
            // Kembali ke halaman home_page
            onBackPressed()
        }

        btnUpdateProfile.setOnClickListener {

            val updatedUser = User(
                id = user.id,
                username = etUsername.text.toString(),
                email = etEmail.text.toString(),
                phoneNumber = etPhone.text.toString(),
                address = etAddress.text.toString(),
                age = etAge.text.toString().toInt(),

                allergies = null,
                gender = String(),
                herbalConsumption = null,
                internalDisease = null,
                liverKidneyIssue = null,
                pregnancyStatus = null
            )
            CoroutineScope(Dispatchers.IO).launch {
                userDao.updateUser(updatedUser)
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Profile Updated", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}