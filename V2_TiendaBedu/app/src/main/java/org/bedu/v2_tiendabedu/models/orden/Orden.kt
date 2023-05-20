package org.bedu.v2_tiendabedu.models.orden

import impuesto.Impuesto
import org.bedu.v2_tiendabedu.models.inventario.Inventario
import java.util.*



enum class Estados {
    PENDIENTE, COMPLETA, SURTIDO
}

class Orden(
    var noOrden: Int = 0,
) : Impuesto {

    private var objetoProducto: MutableMap<String, Any> = mutableMapOf()
    var listaProducto = LinkedList<MutableMap<String, Any>>()
    var statusOrden = Estados.PENDIENTE
    private var totalOrden = 0f

    var fechaCreacion = Date().toString()
    override fun calcularImpuestos(precio: Float): Float {
        return precio * Impuesto.taxIvaMx
    }

    companion object {
        protected var contadorOrden: Int = 0
    }

    init {
        contadorOrden += 1
    }

    init {
        this.noOrden = contadorOrden
    }

    fun agregarProductoOrden(id: Int, numProductos: Int): MutableMap<String, Any> {

        val productoOrden = Inventario.buscarProducto(id)

        if (productoOrden.isEmpty()) {
            println("No hay inventario para el producto Id:$id")
        } else {
            val subtotal = numProductos * productoOrden[0].precio
            val iva: Float = calcularImpuestos(subtotal)
            objetoProducto = mutableMapOf(
                "_id" to productoOrden[0].id,
                "Descripción" to productoOrden[0].descripcion,
                "Cantidad" to numProductos,
                "precio" to productoOrden[0].precio,
                "subtotal" to subtotal,
                "iva" to iva,
                "total" to subtotal + iva,
                "statusProducto" to Estados.PENDIENTE
            )

            listaProducto.addLast(objetoProducto)
            println("productos agregados")
        }
        return objetoProducto
    }

    fun printListaProductos() {
        if (listaProducto.isEmpty()) {
            println("Aún no hay productos agregados")
        } else {
            println("productos: ")
            listaProducto.forEach() {
                println(it)
            }
        }
    }

    fun procesarOrden() {
        if (statusOrden == Estados.COMPLETA) {
            println("La orden ya fue procesada")
        } else {
            println("Procesando orden")
            listaProducto.forEach {
                val idProducto = it["_id"]?.let { it1 -> castingInt(it1) }
                val cantidadProducto = it["Cantidad"]?.let { it2 -> castingInt(it2) }
                if (idProducto != null && cantidadProducto != null &&
                    it["statusProducto"] == Estados.PENDIENTE
                ) {
                    try {
                        Inventario.actualizarStock(
                            idProducto,
                            cantidadProducto, "-"
                        )
                        it["statusProducto"] = Estados.SURTIDO
                        statusOrden = Estados.COMPLETA
                    } catch (e: Error) {
                        println("${e.message} en id:$idProducto ")
                        statusOrden = Estados.PENDIENTE
                    }

                }

            }
        }
    }

    fun castingInt(value: Any): Int {
        return value.toString().toInt()
    }

    fun calcularTotal(): Float {
        totalOrden = 0f
        if (!listaProducto.isEmpty()) {
            for (producto in listaProducto)
                totalOrden += producto["total"].toString().toFloat()
        }
        return totalOrden
    }

    fun actualizarNumCantidadProducto(id: Int, cantidad: Int) {
        val objetoLista = listaProducto.filter { objetoProducto -> objetoProducto["_id"] == id }
        if (objetoLista.isEmpty()) {
            println("Producto no existe")
        } else {
            objetoLista[0]["Cantidad"] = cantidad
        }
    }

    fun ticketVenta(){
        val ticket= ("""
            _________________________________________________________
            |                Tienda Bedu S.A de C.V                  |
            |        Terminal móvil de venta PVM Bedu ver1.0         |
            |________________________________________________________|
            | #  |          Producto         |  Precio  |    Total   |
            |____|___________________________|__________|____________|
        """.trimIndent())

        if (statusOrden == Estados.COMPLETA){
            println(ticket)
            listaProducto.forEach(){
                val formaProducto = String.
                format("|%-4s|%-27s|$%-9.2f|$%-11.2f|\n",
                    it.get("Cantidad"), it.get("Descripción"),
                    it.get("precio"),it.get("total") )
                print(formaProducto)
            }
            println("____________________________________" +
                    "______________________")
            val textTotal = "Total"
            val total = String.format("|%-44s$%.2f|\n",textTotal, calcularTotal())
            print(total)
            println("____________________________________" +
                    "______________________")


        }else{
            println("Orden no procesada")
        }

    }
}

fun main(args: Array<String>) {


    Inventario.agregarProductoInventario(
        nombre = "Zapato", descripcion = "Zapato Blanco",
        tipo = "Calzado", modelo = "ZAP-00", precio = 330f, stock = 50, talla = 22.5f
    )

    Inventario.agregarProductoInventario(
        nombre = "Pantalon", descripcion = "Pantalón mezclila azul",
        tipo = "Ropa", modelo = "PA-00", precio = 700f, stock = 20, talla = 32F
    )

    Inventario.agregarProductoInventario(
        nombre = "Televisión", descripcion = "TV samsung 40 pulgadas",
        tipo = "Hogar", modelo = "SA-0002", precio = 100500f, stock = 10, numeroSerie = "STVMX-0001"
    )

    Inventario.visualizarInventario()

    val orden1 = Orden()
    orden1.agregarProductoOrden(1, 3)
    orden1.agregarProductoOrden(2, 2)
    orden1.agregarProductoOrden(3, 100)

     orden1.printListaProductos()
//   println( orden1.listaProducto )
    //orden1.procesarOrden()
   // orden1.actualizarNumCantidadProducto(3, 2)
    //orden1.procesarOrden()
   // orden1.printListaProductos()

    //Inventario.visualizarInventario()
//    orden1.visualizarListaProductos()
      orden1.ticketVenta()
}


