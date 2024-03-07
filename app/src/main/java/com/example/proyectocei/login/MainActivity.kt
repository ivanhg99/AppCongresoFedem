package com.example.proyectocei.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.proyectocei.*
import com.example.proyectocei.api.Constants
import com.example.proyectocei.api.FedemApplication
import com.example.proyectocei.databinding.ActivityMainBinding
import com.example.proyectocei.principal.PrincipalAjenoActivity
import com.example.proyectocei.principal.PrincipalSocioActivity
import com.example.proyectocei.socios.SocioService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val guardaUsuario = getSharedPreferences("guardaUsuario", MODE_PRIVATE)
        val guardaContrasenya = getSharedPreferences("guardaContrasenya", MODE_PRIVATE)

        binding.tieUsuario.setText(guardaUsuario.getString("guardaUsuario", ""))
        binding.tieContrasenya.setText(guardaContrasenya.getString("guardaContrasenya", ""))

        binding.checkbox.setOnClickListener {
            if (binding.checkbox.isChecked) {
                shared(guardaUsuario, guardaContrasenya)
            }
        }

        binding.login.setOnClickListener {
            login()
        }

    }

    private fun login() {
        val user = binding.tieUsuario.text.toString().trim()
        val password = binding.tieContrasenya.text.toString().trim()
        val prin_socio = Intent(this, PrincipalSocioActivity::class.java)
        val prin_ajeno = Intent(this, PrincipalAjenoActivity::class.java)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.FEDEM_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LoginService::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = service.loginUser(user, password)
                withContext(Dispatchers.Main) {
                    FedemApplication.asistente = result
                    try {
                        val service2 = retrofit.create(SocioService::class.java)
                        val result2 = service2.getSocio(FedemApplication.asistente.id)
                        val socio = result2.body()!!
                        startActivity(prin_socio)
                    } catch (e: Exception) {
                        startActivity(prin_ajeno)
                    }
                }
            } catch (e: Exception) {
                (e as? HttpException)?.let {
                    when (it.code()) {
                        400 -> {
                            updateUI(getString(R.string.main_error_server))
                        }
                        else ->
                            updateUI(getString(R.string.main_error_response))
                    }
                }
            }
        }
    }

    private suspend fun updateUI(result: String) =
        withContext(Dispatchers.Main) {
            binding.tvResult.visibility = View.VISIBLE
            binding.tvResult.text = result
        }

    private fun shared(guardaUsuario: SharedPreferences, guardaContrasenya: SharedPreferences) {
        guardaUsuario.edit().putString("guardaUsuario", binding.tieUsuario.text.toString().trim())
            .apply()
        guardaContrasenya.edit()
            .putString("guardaContrasenya", binding.tieContrasenya.text.toString().trim())
            .apply()
    }
}