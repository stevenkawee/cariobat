package com.example.cariobat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Chatbot : AppCompatActivity() {
    private lateinit var btnback2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbot)
        btnback2 = findViewById(R.id.btnback2)

        btnback2.setOnClickListener {
            val intent = Intent(this, home_page::class.java)
            startActivity(intent)
            finish()
        }
    }
}