package impuesto

interface Impuesto {

    companion object {
        val taxIvaMx = 0.16f
    }

    fun calcularImpuestos(precio: Float): Float
}