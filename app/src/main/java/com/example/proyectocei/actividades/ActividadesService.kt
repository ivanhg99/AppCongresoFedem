package com.example.proyectocei.actividades

import com.example.proyectocei.api.Constants
import retrofit2.Call
import retrofit2.http.GET

interface ActividadesService {
    @GET(value = Constants.GET_ACTIVIDADES)
    fun getActividades(): Call<List<ActividadEntity>>
}