package com.example.proyectocei.slider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.proyectocei.api.FedemApplication
import com.example.proyectocei.R
import com.example.proyectocei.databinding.ActivityPerfilBinding
import com.google.android.material.checkbox.MaterialCheckBox

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        datos()
        volver()
    }

    private fun datos() {
        Glide.with(this)
            .load(FedemApplication.asistente.imagen)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.fotoPerfil)

        binding.nomUsuario.text = FedemApplication.asistente.nombreUsuario

        binding.nombre.text = FedemApplication.asistente.nombre

        binding.apellido.text = FedemApplication.asistente.apellido

        binding.genero.text = FedemApplication.asistente.genero

        binding.email.text = FedemApplication.asistente.correo

        binding.cp.text = FedemApplication.asistente.codigoPostal

        binding.provincia.text = FedemApplication.asistente.provincia
    }

    private fun volver() {
        val boton = findViewById<MaterialCheckBox>(R.id.volver)
        boton.setOnClickListener {
            finish()
        }
    }
}