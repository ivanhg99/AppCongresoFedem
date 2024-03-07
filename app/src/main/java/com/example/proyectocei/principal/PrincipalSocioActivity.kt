package com.example.proyectocei.principal

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.proyectocei.empresa.EmpresaEntity
import com.example.proyectocei.entrada.EntradaActivity
import com.example.proyectocei.R
import com.example.proyectocei.api.Constants
import com.example.proyectocei.mapa.UbicacionActivity
import com.example.proyectocei.comidas.ComidasActivity
import com.example.proyectocei.databinding.ActivityPrincipalSocioBinding
import com.example.proyectocei.eventos.EventosActivity
import com.example.proyectocei.login.MainActivity
import com.example.proyectocei.patrocinadores.PatrocinadoresService
import com.example.proyectocei.slider.AcercadeActivity
import com.example.proyectocei.slider.PerfilActivity
import com.google.android.material.checkbox.MaterialCheckBox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PrincipalSocioActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityPrincipalSocioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_socio)

        binding = ActivityPrincipalSocioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawer: DrawerLayout = binding.principal
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.cerrado)

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        evento()
        comida()
        entrada()
        ubicacion()
        slider()
        setupImagesCarousel()
    }

    private fun evento() {
        val boton = findViewById<MaterialCheckBox>(R.id.eventos)
        boton.setOnClickListener {
            val intent = Intent(this, EventosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun comida() {
        val boton = findViewById<MaterialCheckBox>(R.id.comidas)
        boton.setOnClickListener {
            val intent = Intent(this, ComidasActivity::class.java)
            startActivity(intent)
        }
    }

    private fun ubicacion() {
        val boton = findViewById<Button>(R.id.ubicacion)
        boton.setOnClickListener {
            val intent = Intent(this, UbicacionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun entrada() {
        val boton = findViewById<MaterialCheckBox>(R.id.entrada)
        boton.setOnClickListener {
            val intent = Intent(this, EntradaActivity::class.java)
            startActivity(intent)
        }
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

    private fun setupImagesCarousel() {
        val imagen = mutableListOf<CarouselItem>()
        val lista = mutableListOf<EmpresaEntity>()
        val carrusel = binding.verPatrocinadores

        val service = retro().create(PatrocinadoresService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val call = service.getPatrocinadores()

            withContext(Dispatchers.Main) {
                val patrocinadores = call.body()!!
                for (i in patrocinadores) {
                    val foto = CarouselItem(i.empresaCif.logo)
                    imagen.add(foto)
                    lista.add(i.empresaCif)
                }
                carrusel.addData(imagen)
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

    fun aceptar() {
        val t = Toast.makeText(this, "Se cerró la sesión correctamente", Toast.LENGTH_SHORT)
        t.show()
        finish()
    }

    override fun onBackPressed() {
        val dialogo1: AlertDialog.Builder = AlertDialog.Builder(this)
        dialogo1.setTitle("Importante")
        dialogo1.setMessage("¿Seguro que quieres cerrar sesión?")
        dialogo1.setCancelable(false)

        dialogo1.setPositiveButton(
            "Cancelar"
        ) { _, _ -> }

        dialogo1.setNegativeButton(
            "Confirmar"
        ) { _, _ -> aceptar() }
        dialogo1.show()
    }
}
