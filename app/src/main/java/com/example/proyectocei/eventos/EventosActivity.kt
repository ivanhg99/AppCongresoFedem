package com.example.proyectocei.eventos

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectocei.*
import com.example.proyectocei.actividades.ActividadEntity
import com.example.proyectocei.api.Constants
import com.example.proyectocei.databinding.ActivityEventosBinding
import com.example.proyectocei.listener.OnClickListener
import com.example.proyectocei.login.MainActivity
import com.example.proyectocei.slider.AcercadeActivity
import com.example.proyectocei.slider.PerfilActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventosActivity : AppCompatActivity(), OnClickListener {

    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityEventosBinding

    private lateinit var eventoAdapter: EventoAdapter

    private lateinit var mLinearLayout: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)

        binding = ActivityEventosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawer: DrawerLayout = binding.eventos
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.cerrado)

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        slider()
        sacaEventos()
        setupRecyclerView()
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

    private fun setupRecyclerView() {
        eventoAdapter = EventoAdapter(mutableListOf(), this)
        mLinearLayout = LinearLayoutManager(this)

        binding.RVEventos.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayout
            adapter = eventoAdapter
        }
    }

    private fun retro(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.FEDEM_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    private fun sacaEventos() {
        val service = retro().create(EventosService::class.java)
        val call = service.getEventos()
        call.enqueue(object : Callback<List<EventoEntity>> {
            override fun onResponse(
                call: Call<List<EventoEntity>>,
                response: Response<List<EventoEntity>>
            ) {
                eventoAdapter.setEventos(response.body()!!)
            }

            override fun onFailure(call: Call<List<EventoEntity>>, t: Throwable) {
                service.getEventos().cancel()
            }
        })
    }


    override fun onClick(eventoEntity: EventoEntity) {
        val intent = Intent(this, InfoEventoActivity::class.java)
        intent.putExtra("id", eventoEntity.id)
        startActivity(intent)

    }

    override fun onClickAc(actividadEntity: ActividadEntity) {
        TODO("Not yet implemented")
    }

}
