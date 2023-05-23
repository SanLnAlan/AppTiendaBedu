package org.bedu.v2_tiendabedu

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.bedu.v2_tiendabedu.models.orden.Orden
import org.bedu.v2_tiendabedu.models.producto.Producto
import kotlin.properties.Delegates

var orden = Orden()

class ProductAdapter(val productList: List<Producto>): RecyclerView.Adapter<ProductAdapter.ProductHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductHolder(layoutInflater.inflate(R.layout.item_producto,parent,false))


    }

    override fun getItemCount(): Int = productList.size

    //private lateinit var increase_button : Button
    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.render(productList[position])
    }

    class ProductHolder(val view: View): RecyclerView.ViewHolder(view){
        private var quantityNumber by Delegates.notNull<Int>()

        private val photo: ImageView = view.findViewById<ImageView>(R.id.productImageV)
        fun render(productList: Producto){
            view.findViewById<TextView>(R.id.productNameTv).text = productList.nombre
            view.findViewById<TextView>(R.id.productDescriptionTv).text = productList.descripcion
            view.findViewById<TextView>(R.id.productPriceTv).text = "$" + productList.precio.toString()
            Glide.with(photo.context).load(productList.imgUrl).
            override(100, 100).into(photo)

            val increaseButton = view.findViewById<Button>(R.id.increase)
            val decreaseButton = view.findViewById<Button>(R.id.decrease)

            if (orden.listaProducto.isEmpty()){
                quantityNumber = 0
                Log.d("Test", "ListaV-> $orden.printListaProductos()" )
            }else{
                Log.d("Test", "ListaLL-> $orden.printListaProductos()" )
                val objetoLista = orden.listaProducto.
                filter { objetoProducto -> objetoProducto["_id"] == productList.id }
                if (objetoLista.isEmpty()){
                    quantityNumber=0
                }else{quantityNumber =objetoLista[0]["Cantidad"].toString().toInt()}

                updateData(quantityNumber)
            }

            increaseButton.setOnClickListener {
                quantityNumber += 1
                if (quantityNumber == 1){
                    orden.agregarProductoOrden(productList.id,quantityNumber)
                }else if(quantityNumber > 1){
                    orden.actualizarNumCantidadProducto(productList.id,quantityNumber)
                }
                updateData(quantityNumber)
            }

            decreaseButton.setOnClickListener {
                if (quantityNumber > 0){
                    quantityNumber -= 1
                    if(quantityNumber==0){
                        orden.eliminarProducto(productList.id)
                    }else{
                        orden.actualizarNumCantidadProducto(productList.id,quantityNumber)
                    }
                    updateData(quantityNumber)
                }
            }
        }
        private fun updateData(quantityNumber: Int) {
            val quantityView = view.findViewById<TextView>(R.id.integer_number)
            quantityView.text = "" + quantityNumber.toString()
        }
    }
}