package com.example.cariobat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class home_page : AppCompatActivity() {
    private lateinit var container: FragmentContainerView
    private lateinit var tvNamaLogin: TextView
    private lateinit var textView9: TextView

    private var username: String = ""
    private var email: String = ""
    private var phoneNumber: String = ""
    private var address: String = ""
    private var age: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        container = findViewById(R.id.fragmentContainerView3)


        val email = intent.getStringExtra("EMAIL") ?: ""
        val phoneNumber = intent.getStringExtra("PHONE_NUMBER") ?: ""
        val address = intent.getStringExtra("ADDRESS") ?: ""
        val age = intent.getIntExtra("AGE", 0)

        tvNamaLogin = findViewById(R.id.tvnamalogin)
        textView9 = findViewById(R.id.textView9)
        container = findViewById(R.id.fragmentContainerView3)
        val username = intent.getStringExtra("USERNAME") ?: "Pengguna"
        val saldo = intent.getStringExtra("saldo") ?: "0"
        tvNamaLogin.text = "$username!"
        textView9.text = "Saldo anda : Rp$saldo"

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(container.id, catalogobat())
//                .commitNow()
//        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.catalog_home -> {
                container.findNavController().navigate(R.id.action_global_catalogobat)
                return true
            }
            R.id.catalog_profile -> {
                val intent = Intent(this, profile::class.java).apply {
                    putExtra("USERNAME", username) // Ganti username dengan data pengguna yang login
                    putExtra("EMAIL", email) // Ganti email dengan data pengguna yang login
                    putExtra("PHONE_NUMBER", phoneNumber) // Ganti phoneNumber dengan data pengguna yang login
                    putExtra("ADDRESS", address) // Ganti address dengan data pengguna yang login
                    putExtra("AGE", age) // Ganti age dengan data pengguna yang login
                }
                startActivity(intent)
                return true
            }
            R.id.catalog_logout ->{
                logoutUser()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    private fun logoutUser() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}