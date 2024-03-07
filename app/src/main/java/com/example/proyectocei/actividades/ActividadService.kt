package com.example.proyectocei.actividades

import com.example.proyectocei.api.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ActividadService {
    @GET(value = Constants.GET_ACTIVIDAD)
    fun getActividad(

        @Path(value = "id") id: Long

    ): Call<ActividadResponse>
}