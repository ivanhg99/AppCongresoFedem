package com.example.proyectocei.comidas

data class ComidasEntity(
    var id: Long = 0,
    var nombre: String,
    var tipoComida: String,
    var descripcion: String,
    var imagen: String
)
