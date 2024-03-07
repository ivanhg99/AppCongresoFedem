package com.example.proyectocei.eventos

import com.google.gson.annotations.SerializedName

data class
EventoResponse(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("imagen") val imagen: String,
    @SerializedName("lugar") val lugar: String,
    @SerializedName("horaInicio") val horaInicio: String,
    @SerializedName("horaFin") val horaFin: String,
    @SerializedName("descripcion") val descripcion: String
)

