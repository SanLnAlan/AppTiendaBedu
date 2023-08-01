package org.bedu.v2_tiendabedu.utilitis

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.bedu.v2_tiendabedu.models.user.ResponseLogin
import org.bedu.v2_tiendabedu.models.user.User
import org.bedu.v2_tiendabedu.models.user.UserDetail

class SharedPrfs {

    companion object{

        private const val USUARIOS_PREFS = "usuarios_prefs"
        private const val FECHA_CADUCIDAD = "expiry"
        private const val USER = "username"
        private const val TOKEN = "token"
        private lateinit var preferences: SharedPreferences


        fun saveUserPreferences(user: ResponseLogin, context: Context){
            preferences = context.getSharedPreferences(USUARIOS_PREFS, Context.MODE_PRIVATE)
            preferences.edit()
                .putString(FECHA_CADUCIDAD, user.expiry)
                .putString(USER, user.user.username)
                .putString(TOKEN, user.token)
                .apply()
        }

        fun saveUserPreferencesFromGoogle(email: String, token: String, caducidad: String, context: Context){
            preferences = context.getSharedPreferences(USUARIOS_PREFS, Context.MODE_PRIVATE)
            preferences.edit()
                .putString(FECHA_CADUCIDAD, "")
                .putString(USER, email)
                .putString(TOKEN, "")
                .apply()
        }

        fun getUserPreferences(context: Context): List<String?> {
            preferences = context.getSharedPreferences(USUARIOS_PREFS, Context.MODE_PRIVATE)

            val user = preferences.getString(USER, "")
            val token = preferences.getString(TOKEN,"")
            val expiry = preferences.getString(FECHA_CADUCIDAD,"")

            if(user == "" || token == "" || expiry==""){
                return  emptyList()
            }else{
                return listOf(user, token, expiry)
            }

        }

        fun cleanUserPreferences(context: Context){
            preferences = context.getSharedPreferences(USUARIOS_PREFS, Context.MODE_PRIVATE)
            preferences.edit()
               .putString(FECHA_CADUCIDAD, "")
                .putString(USER, "")
                .putString(TOKEN, "")
                .apply()
        }

        fun updateEmail(email: String, context: Context){
            preferences = context.getSharedPreferences(USUARIOS_PREFS, Context.MODE_PRIVATE)
            preferences.edit()
                .putString(USER, email)
                .apply()
        }
    }
}