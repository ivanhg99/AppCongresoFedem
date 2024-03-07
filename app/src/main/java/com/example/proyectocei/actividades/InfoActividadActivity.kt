package com.example.proyectocei.actividades

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.proyectocei.*
import com.example.proyectocei.databinding.ActivityInfoActividadesBinding
import com.example.proyectocei.login.MainActivity
import com.example.proyectocei.slider.AcercadeActivity
import com.example.proyectocei.slider.PerfilActivity

class InfoActividadActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityInfoActividadesBinding

    private lateinit var mInfoViewModel: InfoActividadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_evento)

        binding = ActivityInfoActividadesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawer: DrawerLayout = binding.info
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.cerrado)

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        slider()
        setupViewModel()
    }

    private fun slider() {
        val link = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://fedem.es/")
        )

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.perfil -> startActivity(
                    Intent(
                        this,
                        PerfilActivity::class.java
                    )
                )
                R.id.contacta -> startActivity(link)
                R.id.acercade -> startActivity(
                    Intent(
                        this,
                        AcercadeActivity::class.java
                    )
                )
                R.id.cerrarsesion -> startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViewModel() {
        val id = intent.extras?.getLong("id")

        mInfoViewModel = ViewModelProvider(this)[InfoActividadViewModel::class.java]

        mInfoViewModel.getActividad(id!!)

        mInfoViewModel.actividad.observe(this) { actividad ->

            binding.nombreActividad.text = actividad.nombre
            binding.descripcionActividad.text = actividad.descripcion
            binding.horaInicio.text = actividad.horaInicio
            binding.horaFin.text = actividad.horaFin

            Glide.with(this)
                .load(actividad.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.portadaActividad)


        }
    }
}