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
import org.bedu.v2_tiendabedu.models.orden.arregloOrden
import org.bedu.v2_tiendabedu.models.producto.Producto
import kotlin.reflect.typeOf


val orden = Orden()
//arregloOrden.addLast(orden)

class ProductAdapter(val productList: List<org.bedu.v2_tiendabedu.models.producto.Producto>): RecyclerView.Adapter<ProductAdapter.ProductHolder>(){

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
        //val orden = Orden()
        //arregloOrden.addLast(orden)

       private val photo: ImageView = view.findViewById<ImageView>(R.id.productImageV)
        fun render(productList: Producto){
            view.findViewById<TextView>(R.id.productNameTv).text = productList.nombre
            view.findViewById<TextView>(R.id.productDescriptionTv).text = productList.descripcion
            view.findViewById<TextView>(R.id.productPriceTv).text = "$" + productList.precio.toString()
            Glide.with(photo.context).load(productList.imgUrl).
            override(100, 100).into(photo)


            val increaseButton = view.findViewById<Button>(R.id.increase)
            val decreaseButton = view.findViewById<Button>(R.id.decrease)

            //var quantityNumber = 0
            var quantityNumber = orden.getObjetoProducto()["Cantidad"].toString().toIntOrNull() ?: 0


            increaseButton.setOnClickListener {
                quantityNumber += 1
                if (quantityNumber == 1){
                    orden.agregarProductoOrden(productList.id,quantityNumber)
                }else if(quantityNumber > 1){
                    orden.actualizarNumCantidadProducto(productList.id,quantityNumber)
                }
               // Log.i("tag ->","${orden.printListaProductos()}")
               // Log.i("otrotag ->","${orden.getObjetoProducto()["Cantidad"]}")

                updateData(quantityNumber)
            }

            decreaseButton.setOnClickListener {
                if (quantityNumber > 0){
                    quantityNumber -= 1
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