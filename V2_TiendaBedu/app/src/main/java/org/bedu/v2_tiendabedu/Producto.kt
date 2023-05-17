package org.bedu.v2_tiendabedu

data class Producto (
        var id: Int = 0,
        var nombre: String,
        var descripcion: String,
        var tipo: String,
        var modelo: String,
        var precio: Float,
        var stock: Int = 0,
        var image: Int
)
