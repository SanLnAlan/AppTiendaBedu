package org.bedu.v2_tiendabedu.utilitis


import android.util.Log
import org.bedu.v2_tiendabedu.models.user.ResponseLogin
import org.bedu.v2_tiendabedu.models.user.UserLogin
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class TiendaService {

        // Instancia de Retrofit
       private fun getRetrofit():Retrofit{
            return Retrofit.Builder()
                .baseUrl("http://192.168.1.134:8005/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    val apiService = getRetrofit().create(ApiService::class.java)


}
