package com.example.primeraprueba.firstapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primeraprueba.R

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etName = findViewById<AppCompatEditText>(R.id.etName)

        val btnStart = findViewById<AppCompatButton>(R.id.btnStart)
        btnStart.setOnClickListener {
            val name = etName.text.toString()
            if(name.isNotEmpty()){

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("EXTRA_NAME",name)//para enviar variables extras

                startActivity(intent)
            }
        }
    }
}