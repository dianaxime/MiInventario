package com.example.miinventario.models

class Fila (val producto: Producto) {
    private var cantidad:Int=0
    override fun toString(): String {
        return "\$producto - \$cantidad"
    }
    fun setCantidad(ncant:Int){
        this.cantidad =ncant
    }
    fun getCantidad():Int{return cantidad}
}