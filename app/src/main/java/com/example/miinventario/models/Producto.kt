package com.example.miinventario.models

class Producto (val producto: String,
                val codigo: String) {


    override fun toString(): String {
        return "\$codigo - \$producto"
    }
}