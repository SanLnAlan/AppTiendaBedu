package org.bedu.v2_tiendabedu

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.bedu.v2_tiendabedu.utilitis.Tabla

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarritoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarritoFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        buttonPagar.setOnClickListener {
            Toast.makeText(context, "${orden.printListaProductos()}", Toast.LENGTH_SHORT)
        }

        val tabla = Tabla(context as Activity?, view.findViewById(R.id.tabla) )
        tabla.agregarCabecera(R.array.cabecera_tabla)
        for (i in 0..14) {
            val elementos = ArrayList<String>()
            elementos.add(Integer.toString(i))
            elementos.add("Casilla [$i, 0]")
            elementos.add("Casilla [$i, 1]")
            elementos.add("Casilla [$i, 2]")
            elementos.add("Casilla [$i, 3]")
            tabla.agregarFilaTabla(elementos)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarritoFragment.
         */
        // TODO: Rename and change types and number of parameters
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