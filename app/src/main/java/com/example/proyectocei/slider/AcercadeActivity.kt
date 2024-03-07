package com.example.proyectocei.slider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectocei.R
import com.google.android.material.checkbox.MaterialCheckBox

class AcercadeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acercade)

        volver()
    }

    private fun volver() {
        val boton = findViewById<MaterialCheckBox>(R.id.volver)
        boton.setOnClickListener {
            finish()
        }
    }
}