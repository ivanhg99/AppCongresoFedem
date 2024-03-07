package com.example.proyectocei.comidas

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.proyectocei.R
import com.example.proyectocei.databinding.ActivityVistaComidasBinding

class ComidaAdapter(
    private var comidas: List<ComidasEntity>
) :
    RecyclerView.Adapter<ComidaAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(
            R.layout.activity_vista_comidas,
            parent, false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comida = comidas.get(position)

        with(holder) {
            binding.nombreRestaurante.text = comida.nombre
            binding.tipoComida.text = comida.tipoComida
            binding.desc.text = comida.descripcion

            Glide.with(mContext)
                .load(comida.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.logoRestaurante)
        }
    }

    override fun getItemCount(): Int = comidas.size

    @SuppressLint("NotifyDataSetChanged")
    fun setComidas(comidas: List<ComidasEntity>) {
        this.comidas = comidas
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ActivityVistaComidasBinding.bind(view)
    }
}