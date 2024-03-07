package com.example.proyectocei.eventos

import com.example.proyectocei.api.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EventoService {
    @GET(value = Constants.GET_EVENTO)
    fun getEvento(

        @Path(value = "id") id: Long

    ): Call<EventoResponse>
}