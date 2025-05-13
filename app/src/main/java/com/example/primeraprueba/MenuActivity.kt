package com.example.primeraprueba

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primeraprueba.firstapp.FirstAppActivity
import com.example.primeraprueba.imcalculator.ImcCalculatorActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSaludapp = findViewById<Button>(R.id.btnSaludapp)
        val btnIMCApp = findViewById<Button>(R.id.btnIMCApp)
        btnSaludapp.setOnClickListener { navigateToSaludapp() }
        btnIMCApp.setOnClickListener { navigateToIMCApp() }

    }

    private fun navigateToIMCApp() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSaludapp(){
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }
}