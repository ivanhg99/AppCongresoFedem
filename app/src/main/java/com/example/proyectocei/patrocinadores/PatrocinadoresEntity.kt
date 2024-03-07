package com.example.proyectocei.patrocinadores

import com.example.proyectocei.empresa.EmpresaEntity

data class PatrocinadoresEntity(
    var id: Long = 0,
    var empresaCif: EmpresaEntity
)
