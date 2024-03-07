package com.example.proyectocei.listener

import com.example.proyectocei.actividades.ActividadEntity
import com.example.proyectocei.eventos.EventoEntity

interface OnClickListener {
    fun onClick(eventoEntity: EventoEntity)
    fun onClickAc(actividadEntity: ActividadEntity)
}