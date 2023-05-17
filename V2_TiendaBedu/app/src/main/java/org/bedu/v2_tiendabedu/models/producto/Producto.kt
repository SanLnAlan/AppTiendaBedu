package org.bedu.v2_tiendabedu.models.producto

// Clase abstracta del producto
abstract class Producto(
    var id: Int = 0,
    var nombre: String,
    var descripcion: String,
    var tipo: String,
    var modelo: String,
    var precio: Float,
    var stock: Int = 0
) {
    // Variable estática
    companion object {
        protected var contadorProducto: Int = 0
    }

    // Inicia el contador de productos instanciados
    init {
        contadorProducto += 1
    }

    // Asigna como id el contador
    init {
        this.id = contadorProducto
    }

    abstract fun descripcionProducto(): String?

    open fun aumentarStock(stock: Int): Int {
        return stock.let { this@Producto.stock += it; stock }
    }

    open fun descontarStock(stock: Int): Int {
        val difSctok: Int = this.stock - stock
        if (difSctok >= 0) {
            return stock.let { this@Producto.stock -= it; stock }

        } else {
            throw Error("Existencia insuficiente en inventario de: ${this.nombre}")
        }

    }

    override fun toString(): String {
        return """
           Id del producto: ${this.id}
           Nombre del producto: ${this.nombre} 
           Descripción del producto: ${this.descripcion} 
           Precio del producto: ${this.precio} 
           Modelo del producto: ${this.modelo}
           Tipo del producto: ${this.tipo}
           Stock del producto: ${this.stock}
        """.trimIndent()
    }

}
