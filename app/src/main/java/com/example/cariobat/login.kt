package com.example.cariobat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class login : AppCompatActivity() {



    private lateinit var btnloginuser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btnloginuser = findViewById(R.id.btnloginuser)
        btnloginuser.setOnClickListener {
            val intent = Intent(this, home_page::class.java)
            startActivity(intent)
            finish()
        }

    }
}