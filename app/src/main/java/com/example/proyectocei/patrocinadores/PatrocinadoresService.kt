package com.example.proyectocei.patrocinadores

import com.example.proyectocei.api.Constants
import retrofit2.Response
import retrofit2.http.GET

interface PatrocinadoresService {
    @GET(value = Constants.GET_PATROCINADORES)
    suspend fun getPatrocinadores(): Response<List<PatrocinadoresEntity>>
}