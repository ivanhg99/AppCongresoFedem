package com.example.proyectocei.entrada

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.proyectocei.R
import com.example.proyectocei.api.FedemApplication
import com.example.proyectocei.databinding.ActivityEntradaBinding
import com.example.proyectocei.login.MainActivity
import com.example.proyectocei.slider.AcercadeActivity
import com.example.proyectocei.slider.PerfilActivity

class EntradaActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityEntradaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrada)

        binding = ActivityEntradaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawer: DrawerLayout = binding.entradas
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.cerrado)

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        slider()
        imagen()
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

    @SuppressLint("SetTextI18n")
    private fun imagen(
        url: String = "https://borealtech.com/wp-content/uploads/2018/" +
                "10/codigo-qr-1024x1024-1.jpg"
    ) {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.imagen)

        binding.nombre.text =
            FedemApplication.asistente.nombre + " " + FedemApplication.asistente.apellido
    }
}