package com.example.proyectocei.actividades

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectocei.api.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InfoActividadViewModel : ViewModel() {

    val actividad = MutableLiveData<ActividadResponse>()

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.FEDEM_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ActividadService::class.java)

    fun getActividad(id: Long) {

        service.getActividad(id).enqueue(object : Callback<ActividadResponse> {
            override fun onResponse(
                call: Call<ActividadResponse>,
                response: Response<ActividadResponse>
            ) {

                response.body()?.let {
                    actividad.postValue(it)
                }

            }

            override fun onFailure(
                call: Call<ActividadResponse>,
                t: Throwable
            ) {

                service.getActividad(id).cancel()

            }
        }
        )
    }
}