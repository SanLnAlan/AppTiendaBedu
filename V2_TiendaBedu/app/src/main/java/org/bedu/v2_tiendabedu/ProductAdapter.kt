package org.bedu.v2_tiendabedu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter (val productList: List<Producto>): RecyclerView.Adapter<ProductAdapter.ProductHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductHolder(layoutInflater.inflate(R.layout.item_producto,parent,false))
    }

    override fun getItemCount(): Int = productList.size


    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.render(productList[position])
    }

    class ProductHolder(val view: View): RecyclerView.ViewHolder(view){
        fun render(productList: Producto){
            view.findViewById<TextView>(R.id.productNameTv).text = productList.nombre
            view.findViewById<TextView>(R.id.productDescriptionTv).text = productList.descripcion
            view.findViewById<TextView>(R.id.productPriceTv).text = "$" + productList.precio.toString()

            view.findViewById<ImageView>(R.id.productImageV).setImageResource(productList.image)

        }
    }
}