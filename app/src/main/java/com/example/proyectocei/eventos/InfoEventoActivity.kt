package com.example.proyectocei.eventos

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.proyectocei.*
import com.example.proyectocei.api.Constants
import com.example.proyectocei.databinding.ActivityInfoEventoBinding
import com.example.proyectocei.login.MainActivity
import com.example.proyectocei.ponentes.PonenteService
import com.example.proyectocei.slider.AcercadeActivity
import com.example.proyectocei.slider.PerfilActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InfoEventoActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityInfoEventoBinding

    private lateinit var mInfoViewModel: InfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_evento)

        binding = ActivityInfoEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawer: DrawerLayout = binding.info
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.cerrado)

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.ponenteVer.setOnClickListener {
            verPonentes()
        }
        binding.ponenteOcultar.setOnClickListener {
            ocultarPonentes()
        }

        slider()
        sacaPonentes()
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

        mInfoViewModel = ViewModelProvider(this)[InfoViewModel::class.java]

        mInfoViewModel.getEvento(id!!)

        mInfoViewModel.evento.observe(this) { evento ->

            binding.nombreEvento.text = evento.nombre
            binding.descripcionEvento.text = evento.descripcion
            binding.horaInicio.text = evento.horaInicio
            binding.horaFin.text = evento.horaFin

            Glide.with(this)
                .load(evento.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.portadaEvento)
            if (evento.lugar == "Zona A") {

                Glide.with(this)
                    .load(
                        "https://www.lovevalencia.com/wp-content/uploads/2016/12/" +
                                "plano-expojove-valencia-2019-2020-640x453.png"
                    )
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(binding.mapaEvento)

            } else if (evento.lugar == "Zona B") {

                Glide.with(this)
                    .load(
                        "https://static1.lasprovincias.es/www/multimedia/201812/12/" +
                                "media/cortadas/expojove-2019-ki1G-U601866803989IDE" +
                                "-624x385@Las%20Provincias.png"
                    )
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(binding.mapaEvento)

            } else if (evento.lugar == "Zona C") {

                Glide.with(this)
                    .load("https://emprenderycrecer.emprenemjunts.es/fotos/30239_foto.jpg")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(binding.mapaEvento)
            }

        }
    }

    private fun retro(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.FEDEM_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    @SuppressLint("SetTextI18n")
    private fun sacaPonentes() {
        val id = intent.extras?.getLong("id")
        val service = retro().create(PonenteService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val call = service.getPonentes(id)

            withContext(Dispatchers.Main) {
                val ponentes = call.body()

                binding.descripcionPonente.text =
                    ponentes?.get(0)?.socio?.asistente?.nombre + "\n\n" +
                            ponentes?.get(0)?.socio?.asistente?.biografia
            }
        }
    }

    private fun verPonentes() {
        binding.apply {
            binding.ponenteVer.visibility = View.GONE
            binding.ponenteOcultar.visibility = View.VISIBLE
            binding.descripcionPonente.visibility = View.VISIBLE
        }
    }

    private fun ocultarPonentes() {
        binding.apply {
            binding.ponenteVer.visibility = View.VISIBLE
            binding.ponenteOcultar.visibility = View.GONE
            binding.descripcionPonente.visibility = View.GONE
        }
    }
}