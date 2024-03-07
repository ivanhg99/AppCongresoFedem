package com.example.proyectocei.socios

import com.example.proyectocei.api.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SocioService {

    @GET(value = Constants.GET_SOCIO)
    suspend fun getSocio(

        @Path(value = "id_asistente") id: Long

    ): Response<SocioEntity>
}