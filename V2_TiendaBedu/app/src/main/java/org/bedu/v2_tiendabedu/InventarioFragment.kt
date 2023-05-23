package org.bedu.v2_tiendabedu

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.bedu.v2_tiendabedu.utilitis.HandleLogging.Companion.fieldsValidate
import org.bedu.v2_tiendabedu.models.inventario.Inventario
import kotlin.text.toFloatOrNull as toFloatOrNull1

class InventarioFragment : Fragment() {
    private var valProductName = false
    private var valProductDesc = false
    private var valProductModel = false
    private var valProductPrice = false
    private var valProductStock = false
    private var valCalzadoRopa = false
    private var valHogar = false
    private var typeModel = ""

    private lateinit var inputProductName: EditText
    private lateinit var inputProductDesc: EditText
    private lateinit var inputProductModel: EditText
    private lateinit var inputSerialNumer: EditText
    private lateinit var inputProductSize: EditText
    private lateinit var inputProductPrice: EditText
    private lateinit var inputProductStock: EditText
    private lateinit var spinner: Spinner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = view.findViewById(R.id.static_spinner)
        inputProductName = view.findViewById(R.id.txtProductName)
        inputProductDesc = view.findViewById(R.id.txtProductDesc)
        inputProductModel = view.findViewById(R.id.txtProductModel)
        inputSerialNumer = view.findViewById(R.id.txtSerialNumber)
        inputProductSize= view.findViewById(R.id.txtSize)
        inputProductPrice = view.findViewById(R.id.txtProductPrice)
        inputProductStock = view.findViewById(R.id.txtStockcProduct)
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

        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(val type = parent?.getItemAtPosition(position).toString()){
                    "Calzado", "Ropa"-> {inputProductSize.isEnabled= true
                                         inputSerialNumer.isEnabled = false
                                         valHogar = false
                                         valCalzadoRopa = true
                                         inputSerialNumer.setText("")
                                         typeModel=type
                                         }
                    "Hogar" -> {inputProductSize.isEnabled= false
                        inputSerialNumer.isEnabled = true
                        valHogar = true
                        valCalzadoRopa = false
                        inputProductSize.setText("")
                        typeModel=type
                    }
                    else ->  { typeModel=""
                        valCalzadoRopa = false
                        valHogar = false

                    }
                }
            }
        }
        btnStock.setOnClickListener{
            validateForm()
        }
    }
    private fun valDatos(): Boolean {
        var v = false
        if (valProductName && valProductDesc && valProductModel &&
            valProductPrice && valProductStock) {
            v = true
        }

        return v
    }

    private fun validateForm(){
        val nameProduct = inputProductName.text.toString()
        val descProduct = inputProductDesc.text.toString()
        val modelProduct = inputProductModel.text.toString()
        val priceProduct = inputProductPrice.text.toString()
        val stockProduct = inputProductStock.text.toString()
        val sizeProduct = inputProductSize.text.toString()
        val serialProduct = inputSerialNumer.text.toString()

        if(TextUtils.isEmpty(nameProduct)){
            inputProductName.error = getString(R.string.campo_obligatorio)
            inputProductName.requestFocus()
            valProductName=false
            return
        }else if(fieldsValidate(nameProduct,"ALPHANUMERIC")){
            valProductName=true
        }else{
            inputProductName.error = getString(R.string.campo_error_datos)
            inputProductName.requestFocus()
            valProductName=false
            return
        }

        if(TextUtils.isEmpty(descProduct)){
            inputProductDesc.error = getString(R.string.campo_obligatorio)
            inputProductDesc.requestFocus()
            valProductDesc=false
            return
        }else if(fieldsValidate(descProduct,"ALPHANUMERIC")){
            valProductDesc=true
        }else{
            inputProductDesc.error = getString(R.string.campo_error_datos)
            inputProductDesc.requestFocus()
            valProductDesc=false
            return
        }
        if(TextUtils.isEmpty(modelProduct)){
            inputProductModel.error = getString(R.string.campo_obligatorio)
            inputProductModel.requestFocus()
            valProductModel=false
            return
        }else if(fieldsValidate(modelProduct,"SERIALNUM")){
            valProductModel=true
        }else{
            inputProductModel.error = getString(R.string.campo_error_datos)
            inputProductModel.requestFocus()
            valProductModel=false
            return
        }
        if(valHogar){
            if(TextUtils.isEmpty(serialProduct)){
                inputSerialNumer.error = getString(R.string.campo_obligatorio)
                inputSerialNumer.requestFocus()
                valHogar=false
                return
            }else if(fieldsValidate(serialProduct,"SERIALNUM")){
                valHogar=true
            }else{
                inputSerialNumer.error = getString(R.string.campo_error_datos)
                inputSerialNumer.requestFocus()
                valHogar=false
                return
            }
        }
        if(valCalzadoRopa){
            if(TextUtils.isEmpty(sizeProduct)){
                inputProductSize.error = getString(R.string.campo_obligatorio)
                inputProductSize.requestFocus()
                valCalzadoRopa=false
                return
            }else{
                valCalzadoRopa=true
            }
        }

        if(TextUtils.isEmpty(priceProduct)){
            inputProductPrice.error = getString(R.string.campo_obligatorio)
            inputProductPrice.requestFocus()
            valProductPrice=false
            return
        }else{
            valProductPrice=true
        }
        if(TextUtils.isEmpty(stockProduct)){
            inputProductStock.error = getString(R.string.campo_obligatorio)
            inputProductStock.requestFocus()
            valProductStock=false
            return
        }else{
            valProductStock=true
        }

        if (valDatos()){
            val size: Float = sizeProduct.toFloatOrNull1() ?: 0f
            val stock: Int = stockProduct.toIntOrNull() ?: 0
            val price: Float = priceProduct.toFloatOrNull1() ?: 0f

            Log.d("Datos->", "$size $stock $price $typeModel")

            Inventario.agregarProductoInventario(
                nombre = nameProduct, descripcion = descProduct,
                modelo = modelProduct, tipo = typeModel,
                talla = size, numeroSerie = serialProduct,
                stock = stock, precio = price
            )
            val toast = Toast.makeText(requireContext(), "Guardando producto", Toast.LENGTH_LONG)
            toast.show()
            inputProductName.text.clear()
            inputProductDesc.text.clear()
            inputProductModel.text.clear()
            inputProductPrice.text.clear()
            inputProductStock.text.clear()
            inputProductSize.text.clear()
            inputSerialNumer.text.clear()
            spinner.setSelection(0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventario, container, false)
    }

}