package com.example.proyectocei.eventos

import com.example.proyectocei.api.Constants
import retrofit2.Call
import retrofit2.http.GET

interface EventosService {
    @GET(value = Constants.GET_EVENTOS)
    fun getEventos(): Call<List<EventoEntity>>
}