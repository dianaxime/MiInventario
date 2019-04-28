package com.example.miinventario.models

import android.app.Application

class ShowProducts :Application(){
    companion object {

         val myProductos = arrayListOf<Fila>()
        fun addProduct(newFila: Fila) {
            myProductos.add(newFila)
        }

        fun deleteProduct(exFila: Fila) {
            myProductos.remove(exFila)
        }


        fun getProducts(): ArrayList<Fila> {
            return myProductos
        }

        fun limpiar(){
            myProductos.clear()
        }

        fun showProducts(): String {
            var cadena: String = "Existencia: "
            for (p: Fila in myProductos)
                cadena += p.producto.producto + ";"
            return cadena
        }
    }
}