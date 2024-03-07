package com.example.proyectocei.empresa

data class AsistenteEntity(
    var id: Long = 0,
    var nombre: String,
    var apellido: String,
    var nombreUsuario: String,
    var contrasenya: String,
    var codigoPostal: String,
    var provincia: String,
    var genero: String,
    var imagen: String,
    var correo: String,
    var biografia: String
)