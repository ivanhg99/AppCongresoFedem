package com.example.proyectocei.eventos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectocei.api.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InfoViewModel : ViewModel() {

    val evento = MutableLiveData<EventoResponse>()

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.FEDEM_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(EventoService::class.java)

    fun getEvento(id: Long) {

        service.getEvento(id).enqueue(object : Callback<EventoResponse> {
            override fun onResponse(
                call: Call<EventoResponse>,
                response: Response<EventoResponse>
            ) {

                response.body()?.let {
                    evento.postValue(it)
                }

            }

            override fun onFailure(
                call: Call<EventoResponse>,
                t: Throwable
            ) {

                service.getEvento(id).cancel()

            }
        }
        )
    }
}