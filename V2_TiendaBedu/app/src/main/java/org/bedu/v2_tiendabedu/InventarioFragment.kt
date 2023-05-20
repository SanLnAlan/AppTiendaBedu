package org.bedu.v2_tiendabedu

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.bedu.v2_tiendabedu.utilitis.ERRONAME
import org.bedu.v2_tiendabedu.utilitis.ERRORDESC
import org.bedu.v2_tiendabedu.utilitis.HandleLogging.Companion.fieldsValidate
import org.bedu.v2_tiendabedu.utilitis.OKDESCR
import org.bedu.v2_tiendabedu.utilitis.OKNAME


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InventarioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InventarioFragment : Fragment() {
    var valProductName = false
    var valProductDesc = false
    var valProductModel = false
    var valProductPrice = false
    var valProductStock = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner = view.findViewById<Spinner>(R.id.static_spinner)

        val inputProductName: EditText = view.findViewById(R.id.txtProductName)
        val inputProductDesc: EditText = view.findViewById(R.id.txtProductDesc)
        val inputProductModel: EditText = view.findViewById(R.id.txtProductModel)
        val inputSerialNumer: EditText = view.findViewById(R.id.txtSerialNumber)
        val inputProductSize: EditText = view.findViewById(R.id.txtSize)
        val inputProductPrice: EditText = view.findViewById(R.id.txtProductPrice)
        val inputProductStock: EditText = view.findViewById(R.id.txtStockcProduct)
        val btnStock: Button = view.findViewById(R.id.btnStock)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.type_model,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


    }
    private fun activateButton(): Boolean {
        var v = false
        if (valProductName && valProductDesc && valProductModel &&
            valProductPrice && valProductStock) {
            v = true
        }
        return v
    }
    private  fun showToast(msg: String){
        val toast = Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG)
        toast.show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventario, container, false)



    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InventarioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InventarioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}