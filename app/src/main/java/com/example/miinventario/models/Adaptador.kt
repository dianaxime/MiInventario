package com.example.miinventario.models

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.miinventario.R

class Adaptador (private val inventarioproducts: ArrayList<Fila>) :   RecyclerView.Adapter<Adaptador.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val contentView =LayoutInflater.from(parent.context).inflate(R.layout.mycard, null)
        return ViewHolder(contentView)
    }


    override fun getItemCount(): Int {
        return inventarioproducts.size
    }




    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val filaI =inventarioproducts[p1]
        p0.modproduct.text=filaI.producto.producto
        p0.modcant.text=filaI.getCantidad().toString()
        p0.agregar.setOnClickListener{
            var num=filaI.getCantidad() + 1
            filaI.setCantidad(num)
            p0.modcant.text=filaI.getCantidad().toString()
        }
        p0.quitar.setOnClickListener{
            var num =filaI.getCantidad() - 1
            if (num>=0) filaI.setCantidad(num)
            else {
                num=0
                filaI.setCantidad(num)
            }
            p0.modcant.text=filaI.getCantidad().toString()
        }
//        notifyDataSetChanged()
    }


    fun eliminar (viewHolder: RecyclerView.ViewHolder) {
        inventarioproducts.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
        notifyDataSetChanged()
    }
    fun add (fila:Fila) {
        inventarioproducts.add(fila)
        notifyDataSetChanged()
    }

    fun eliminartodos () {
        inventarioproducts.clear()
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var modproduct: TextView=itemView.findViewById(R.id.producto)
        var modcant: TextView=itemView.findViewById(R.id.cantidad)
        var agregar: Button=itemView.findViewById(R.id.sumar)
        var quitar: Button=itemView.findViewById(R.id.restar)




        override fun onClick(v: View?) {}
    }
}