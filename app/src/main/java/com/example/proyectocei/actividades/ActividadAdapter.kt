package com.example.proyectocei.actividades

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
import com.example.proyectocei.databinding.ActivityVistaActividadesBinding

class ActividadAdapter(
    private var actividades: List<ActividadEntity>,
    private var listener: OnClickListener
) :
    RecyclerView.Adapter<ActividadAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(
            R.layout.activity_vista_actividades, parent,
            false
        )

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actividades = actividades.get(position)

        with(holder) {
            setListener(actividades)
            binding.actividad.text = actividades.nombre
            binding.hora.text = actividades.horaInicio

            Glide.with(mContext)
                .load(actividades.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.caratula)
        }
    }

    override fun getItemCount(): Int = actividades.size

    @SuppressLint("NotifyDataSetChanged")
    fun setActividades(actividades: List<ActividadEntity>) {
        this.actividades = actividades
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ActivityVistaActividadesBinding.bind(view)

        fun setListener(actividadEntity: ActividadEntity) {
            binding.root.setOnClickListener {
                listener.onClickAc(actividadEntity)
            }

            binding.root.setOnLongClickListener {
                true
            }
        }
    }
}