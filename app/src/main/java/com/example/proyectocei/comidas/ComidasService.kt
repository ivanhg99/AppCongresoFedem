package com.example.proyectocei.comidas

import com.example.proyectocei.api.Constants
import retrofit2.Call
import retrofit2.http.GET

interface ComidasService {
    @GET(value = Constants.GET_COMIDA)
    fun getComidas(): Call<List<ComidasEntity>>
}