package org.bedu.v2_tiendabedu.utilitis


import android.content.Context
import android.widget.Toast
import okhttp3.OkHttpClient
import org.bedu.v2_tiendabedu.models.user.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class TiendaService {

        // Instancia de Retrofit
       private fun getRetrofit():Retrofit{
            return Retrofit.Builder()
                .baseUrl("http://192.168.1.64:8005/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

       fun getRetrofitHeaders(token: String):Retrofit{
           return Retrofit.Builder()
               .baseUrl("http://192.168.1.64:8005/")
               .addConverterFactory(GsonConverterFactory.create())
               .client(getclient(token))
               .build()
       }

      private fun getclient(token: String):OkHttpClient =
               OkHttpClient.Builder()
              .addInterceptor(HeaderInterceptor(token))
              .build()



      val apiService = getRetrofit().create(ApiService::class.java)

     companion object{
         fun serviceToken(context: Context): Pair<ApiService, String?> {
             val lisP = SharedPrfs.getUserPreferences(context)
             val user = lisP[0]
             val token  = "Token " + lisP[1]
             val apiTienda = TiendaService()
             val apiServiceToken = apiTienda.getRetrofitHeaders(token).create(ApiService::class.java)
         return apiServiceToken to user
         }

         fun logout(context: Context){
             val (apiLogout, user) = serviceToken(context)
            apiLogout.postLogoutUser().enqueue(object : Callback<Unit>{
                 override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                     if (!response.isSuccessful) {
                         ErrorMessage.messege_error(response)
                     }else{
                         response.message()
                     }
                     Toast.makeText(context, if (response.code()==204)
                         "Sesión finaliza" else "Error", Toast.LENGTH_LONG).show()
                 }
                 override fun onFailure(call: Call<Unit>, t: Throwable) {
                     TODO("Not yet implemented")
                 }
             })

         }

         fun chanceName(context: Context, name:String){
             val (apiName, user) = serviceToken(context)
             val newName = ChangeName(name)
             apiName.putNameUser(email = user!!, newName).enqueue(object : Callback<Unit>{
                 override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                     if (!response.isSuccessful) {
                         ErrorMessage.messege_error(response)
                     }else{
                         response.message()
                     }
                     Toast.makeText(context, if (response.code()==200)
                         "Nombres actualizados" else "Error", Toast.LENGTH_LONG).show()
                 }
                 override fun onFailure(call: Call<Unit>, t: Throwable) {
                     TODO("Not yet implemented")
                 }
             })

         }

         fun chanceSurname(context: Context, surname:String){
             val (apiSurname, user) = serviceToken(context)
             val newSurname = ChangeSurname(surname)
             apiSurname.putSurnameUser(email = user!!, newSurname).enqueue(object : Callback<Unit>{
                 override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                     if (!response.isSuccessful) {
                         ErrorMessage.messege_error(response)
                     }else{
                         response.message()
                     }
                     Toast.makeText(context, if (response.code()==200)
                         "Apellidos actualizados" else "Error", Toast.LENGTH_LONG).show()
                 }
                 override fun onFailure(call: Call<Unit>, t: Throwable) {
                     TODO("Not yet implemented")
                 }
             })

         }

         fun chanceEmail(context: Context, email:String){
             val (apiEmail, user) = serviceToken(context)
             val newEmail = ChangeEmail(email, email)
             apiEmail.putEmailUser(email = user!!, newEmail).enqueue(object : Callback<Unit>{
                 override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                     if (!response.isSuccessful) {
                         ErrorMessage.messege_error(response)
                     }else{
                         response.message()
                     }
                     Toast.makeText(context, if (response.code()==200)
                         "Email actualizados" else "Error", Toast.LENGTH_LONG).show()
                 }
                 override fun onFailure(call: Call<Unit>, t: Throwable) {
                     TODO("Not yet implemented")
                 }
             })

         }

         fun chancePassword(context: Context, password: String, password2: String){
             val (apiPassword, user) = serviceToken(context)
             val newPassword = ChancePassword(password, password2)
             apiPassword.putPasswordlUser(email = user!!, newPassword).enqueue(object : Callback<Unit>{
                 override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                     if (!response.isSuccessful) {
                         ErrorMessage.messege_error(response)
                     }else{
                         response.message()
                     }
                     Toast.makeText(context, if (response.code()==200)
                         "Passwors actualizado" else "Error", Toast.LENGTH_LONG).show()
                 }
                 override fun onFailure(call: Call<Unit>, t: Throwable) {
                     TODO("Not yet implemented")
                 }
             })

         }




     }

}



