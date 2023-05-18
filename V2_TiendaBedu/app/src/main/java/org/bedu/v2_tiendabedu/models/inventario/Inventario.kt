package inventario

import org.bedu.v2_tiendabedu.models.producto.CalzadoRopa
import org.bedu.v2_tiendabedu.models.producto.Producto
import org.bedu.v2_tiendabedu.models.producto.Hogar
import java.util.*

class Inventario {
    companion object {
        var listaDeInventario = LinkedList<Producto>()

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
            numeroSerie: String = " "
        ) {
            val tipoVal = tipo.lowercase()
            if (tipoVal == "ropa" || tipoVal == "calzado") {
                val productoCalzadoRopa = CalzadoRopa(
                    nombre, descripcion, precio, modelo, talla, tipo, stock
                )
                agregarInventario(productoCalzadoRopa)

            } else if (tipoVal == "hogar") {
                val productoHogar = Hogar(
                    nombre, numeroSerie, modelo,
                    descripcion, precio, tipo, stock
                )
                agregarInventario(productoHogar)
            }
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
/*
fun main(args: Array<String>) {

    Inventario.agregarProductoInventario(
        "Zapato Blanco", "Zapato casual",
        "calzado", "B-22", 350f, 20, 22.5f
    )
    Inventario.agregarProductoInventario(
        "Tenis Blanco", "Zapato casual",
        "calzado", "B-23", 550f, 10, 23f
    )
    Inventario.agregarProductoInventario(
        "Refrigerador Mabe", "Refrigerador 10 pies cubicos",
        "Hogar", "Mabe-12", 100000f, 10, numeroSerie = "MB-'1112"
    )

}*/
