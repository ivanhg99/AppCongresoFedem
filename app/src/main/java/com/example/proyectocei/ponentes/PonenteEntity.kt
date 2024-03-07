package com.example.proyectocei.ponentes

import com.example.proyectocei.socios.SocioEntity

data class PonenteEntity(
    var id: Long = 0,
    var socio: SocioEntity
)