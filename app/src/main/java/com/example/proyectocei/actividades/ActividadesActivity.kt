package com.example.proyectocei.actividades

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
import com.example.proyectocei.api.Constants
import com.example.proyectocei.databinding.ActivityActividadesBinding
import com.example.proyectocei.eventos.EventoEntity
import com.example.proyectocei.listener.OnClickListener
import com.example.proyectocei.login.MainActivity
import com.example.proyectocei.slider.AcercadeActivity
import com.example.proyectocei.slider.PerfilActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActividadesActivity : AppCompatActivity(), OnClickListener {

    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityActividadesBinding

    private lateinit var actividadAdapter: ActividadAdapter

    private lateinit var mLinearLayout: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)

        binding = ActivityActividadesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawer: DrawerLayout = binding.actividades
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.cerrado)

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        slider()
        sacaActividades()
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
        actividadAdapter = ActividadAdapter(mutableListOf(), this)
        mLinearLayout = LinearLayoutManager(this)

        binding.RVActividades.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayout
            adapter = actividadAdapter
        }
    }

    private fun retro(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.FEDEM_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    private fun sacaActividades() {
        val service = retro().create(ActividadesService::class.java)
        val call = service.getActividades()
        call.enqueue(object : Callback<List<ActividadEntity>> {
            override fun onResponse(
                call: Call<List<ActividadEntity>>,
                response: Response<List<ActividadEntity>>
            ) {
                actividadAdapter.setActividades(response.body()!!)
            }

            override fun onFailure(call: Call<List<ActividadEntity>>, t: Throwable) {
                service.getActividades().cancel()
            }
        })
    }


    override fun onClick(eventoEntity: EventoEntity) {


    }

    override fun onClickAc(actividadEntity: ActividadEntity) {
        val intent = Intent(this, InfoActividadActivity::class.java)
        intent.putExtra("id", actividadEntity.id)
        startActivity(intent)
    }

}
