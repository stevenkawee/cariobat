package com.example.cariobat

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        container = findViewById(R.id.fragmentContainerView3)

        tvNamaLogin = findViewById(R.id.tvnamalogin)
        container = findViewById(R.id.fragmentContainerView3)
        val username = intent.getStringExtra("USERNAME") ?: "Pengguna"
        tvNamaLogin.text = "Selamat Datang, $username!"

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(container.id, catalogobat())
                .commitNow()
        }
    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.nav_menu,menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.catalog_home -> {
//                // Navigasi ke fragment CatalogObatFragment
//                container.findNavController().navigate(R.id.action_global_catalogobat) // Pastikan kelas tujuan yang benar
//                return true
//            }
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }
}