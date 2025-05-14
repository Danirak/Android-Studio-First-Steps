package com.example.primeraprueba.imcalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primeraprueba.R
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcCalculatorActivity : AppCompatActivity() {

    //Parametros para IC
    private var isMaleSelected = true
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView

    //Parametros para Altura
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private var currentHeight: Int = 120

    //Parametros para peso
    private lateinit var btnSubWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private var currentWeight: Int = 70

    //Parametros para edad
    private lateinit var btnSubAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private var currentAge: Int = 18

    //BtnCalcular
    private lateinit var btnCalculate: Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_imc_calculator)
        val mainLayout = findViewById<ConstraintLayout>(R.id.main)
        val originalTopPadding = mainLayout.paddingTop

        initComponents()
        initListeners()
        initUI()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top + originalTopPadding,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSubWeight = findViewById(R.id.btnSubWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubAge = findViewById(R.id.btnSubAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            this.tvHeight.text = "$currentHeight cm"
        }
        btnSubWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnSubAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight * 10000 / (currentHeight.toDouble() * currentHeight.toDouble())
        return df.format(imc).toDouble()
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }

}