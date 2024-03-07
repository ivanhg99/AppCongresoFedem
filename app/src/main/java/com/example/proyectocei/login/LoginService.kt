package com.example.proyectocei.login

import com.example.proyectocei.empresa.AsistenteEntity
import com.example.proyectocei.api.Constants
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST(Constants.POST_LOGIN)
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): AsistenteEntity
}