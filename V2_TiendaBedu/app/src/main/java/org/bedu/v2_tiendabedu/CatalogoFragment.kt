package org.bedu.v2_tiendabedu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CatalogoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CatalogoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerCatalogo: RecyclerView

    val productos = listOf(
        Producto(0,"jeans","Descripción de jeans","ropa","LEVIS",499f,10,R.drawable.jeans),
        Producto(1,"audífonos","Descripción de audífonos","hogar","SONY",899f,10,R.drawable.headphone),
        Producto(2,"televisión","Descripción de televisión","hogar","SAMSUNG",8299f,10,R.drawable.television),
        Producto(3,"microhondas","Descripción de microhondas","hogar","LG",1399f,10,R.drawable.microhondas),
        Producto(4,"tennis","Descripción de tennis","ropa","CONVERSE",799f,10,R.drawable.tennis)
    )

    fun initRecycler(){
        recyclerCatalogo.layoutManager = LinearLayoutManager(context)
        val adapter = ProductAdapter(productos)
        recyclerCatalogo.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //recyclerCatalogo = requiereView!!.findViewById(R.id.recyclerCatalogo)
        //initRecycler()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalogo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerCatalogo = view.findViewById(R.id.recyclerCatalogo)
        initRecycler()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CatalogoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}