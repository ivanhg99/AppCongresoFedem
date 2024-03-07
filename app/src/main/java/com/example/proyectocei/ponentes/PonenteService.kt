package com.example.proyectocei.ponentes

import com.example.proyectocei.api.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PonenteService {
    @GET(value = Constants.GET_PONENTE)

    suspend fun getPonentes(
        @Path(value = "id_evento") id: Long?
    ): Response<List<PonenteEntity>>
}