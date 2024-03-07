package com.example.proyectocei.actividades

import com.google.gson.annotations.SerializedName

data class ActividadResponse(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("imagen") val imagen: String,
    @SerializedName("horaInicio") val horaInicio: String,
    @SerializedName("horaFin") val horaFin: String,
    @SerializedName("descripcion") val descripcion: String
)

