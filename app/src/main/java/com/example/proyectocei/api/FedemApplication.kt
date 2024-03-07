package com.example.proyectocei.api

import android.app.Application
import com.example.proyectocei.empresa.AsistenteEntity

class FedemApplication : Application() {

    companion object {
        lateinit var asistente: AsistenteEntity
    }
}