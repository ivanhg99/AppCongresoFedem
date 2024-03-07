package com.example.proyectocei.socios

import com.example.proyectocei.empresa.AsistenteEntity

data class SocioEntity(
    var id: Long = 0,
    var asistente: AsistenteEntity
)
