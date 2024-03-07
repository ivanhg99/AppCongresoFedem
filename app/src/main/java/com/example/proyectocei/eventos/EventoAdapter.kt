package com.example.proyectocei.eventos

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.proyectocei.listener.OnClickListener
import com.example.proyectocei.R
import com.example.proyectocei.databinding.ActivityVistaEventosBinding

class EventoAdapter(
    private var eventos: List<EventoEntity>,
    private var listener: OnClickListener
) :
    RecyclerView.Adapter<EventoAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(
            R.layout.activity_vista_eventos, parent,
            false
        )

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eventos = eventos.get(position)

        with(holder) {
            setListener(eventos)
            binding.evento.text = eventos.nombre
            binding.fecha.text = "0" + eventos.dia.toString() + "/0" + eventos.mes.toString()
            binding.hora.text = eventos.horaInicio

            Glide.with(mContext)
                .load(eventos.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.caratula)
        }
    }

    override fun getItemCount(): Int = eventos.size

    @SuppressLint("NotifyDataSetChanged")
    fun setEventos(eventos: List<EventoEntity>) {
        this.eventos = eventos
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ActivityVistaEventosBinding.bind(view)

        fun setListener(eventosEntity: EventoEntity) {
            binding.root.setOnClickListener {
                listener.onClick(eventosEntity)
            }

            binding.root.setOnLongClickListener {
                true
            }
        }
    }
}