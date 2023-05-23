package org.bedu.v2_tiendabedu.models.inventario

import android.util.Log
import org.bedu.v2_tiendabedu.models.producto.CalzadoRopa
import org.bedu.v2_tiendabedu.models.producto.Producto
import org.bedu.v2_tiendabedu.models.producto.Hogar
import java.util.*
var listaDeInventario = LinkedList<Producto>()

class Inventario {
    companion object {
        fun agregarInventario(producto: Producto) {
            listaDeInventario.addLast(producto)
        }
        fun agregarProductoInventario(
            nombre: String,
            descripcion: String,
            tipo: String,
            modelo: String,
            precio: Float,
            stock: Int = 0,
            talla: Float = 0f,
            numeroSerie: String = " ",
            urlImagen: String = "https://tinyurl.com/y6zyt3uu"
        ) {
            val tipoVal = tipo.lowercase()
            if (tipoVal == "ropa" || tipoVal == "calzado") {
                val productoCalzadoRopa = CalzadoRopa(
                    nombre, descripcion, precio, modelo, talla, tipo, stock,urlImagen
                )
                agregarInventario(productoCalzadoRopa)

            } else if (tipoVal == "hogar") {
                val productoHogar = Hogar(
                    nombre, numeroSerie, modelo,
                    descripcion, precio, tipo, stock,urlImagen
                )
                agregarInventario(productoHogar)
            }
            Log.d("Inventario","List-> $listaDeInventario")

        }

        // Se usa una función lambda
        fun buscarProducto(id: Int): List<Producto> {
            return listaDeInventario.filter { Producto -> Producto.id == id }
        }

        fun eliminarProducto(id: Int) {
            val objetoEliminar = buscarProducto(id)
            listaDeInventario.remove(element = objetoEliminar[0])

        }

        fun visualizarInventario() {
            val productos = listaDeInventario
            println(
                """
                Inventario:
                -------------------------------------------------------
                """.trimIndent()
            )
            productos.forEach {
                println(it)
                println(
                    """
                    -------------------------------------------------------
                    """.trimIndent()
                )
            }
        }

        fun actualizarStock(id: Int, cantidad: Int, operacion: String) {
            val productoStock = buscarProducto(id)
            when (operacion) {
                "+", "aumentar" -> {
                    productoStock[0].aumentarStock(cantidad)
                }

                "-", "disminuir" -> {
                    productoStock[0].descontarStock(cantidad)
                }

                else -> println(
                    "Parametro incorrecto use los siguients opciones:" +
                            "+ o aumentar  - o disminuir"
                )

            }
        }
    }
}

