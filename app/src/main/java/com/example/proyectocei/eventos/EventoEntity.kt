package com.example.proyectocei.eventos


data class EventoEntity(
    var id: Long = 0,
    var nombre: String,
    var descripcion: String,
    var dia: Int,
    var mes: Int,
    var horaInicio: String,
    var lugar: String,
    var imagen: String
)