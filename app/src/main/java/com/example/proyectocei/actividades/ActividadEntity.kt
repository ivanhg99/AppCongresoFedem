package com.example.proyectocei.actividades


data class ActividadEntity(
    var id: Long = 0,
    var nombre: String,
    var descripcion: String,
    var horaInicio: String,
    var imagen: String
)