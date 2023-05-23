package org.bedu.v2_tiendabedu

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.bedu.v2_tiendabedu.utilitis.Tabla

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CarritoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrito, container, false)
    }

    private lateinit var buttonPagar : Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonPagar = view.findViewById(R.id.button_pagar)
        val txtTotalPagar = view.findViewById<TextView>(R.id.textTotalOrden)
        buttonPagar.setOnClickListener {

        }

        val tabla = Tabla(context as Activity?, view.findViewById(R.id.tabla) )
        tabla.agregarCabecera(R.array.cabecera_tabla)
        if( orden.listaProducto.isNotEmpty()){
            var totalP = orden.calcularTotal().toString()
            totalP = "$" + totalP
            txtTotalPagar.text = totalP

            for (p in orden.listaProducto){
                val elementos = ArrayList<String>()
                elementos.add(p["_id"].toString())
                elementos.add(p["Descripción"].toString())
                elementos.add(p["Cantidad"].toString())
                elementos.add(p["precio"].toString())
                elementos.add(p["statusProducto"].toString())
                tabla.agregarFilaTabla(elementos)
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarritoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}