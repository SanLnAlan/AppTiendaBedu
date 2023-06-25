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

        fun getUserPreferences(context: Context): List<String?> {
            preferences = context.getSharedPreferences(USUARIOS_PREFS, Context.MODE_PRIVATE)

            val user = preferences.getString(USER, "")
            val token = preferences.getString(TOKEN,"")
            val expiry = preferences.getString(FECHA_CADUCIDAD,"")

            if(user == "" && token == "" && expiry==""){
                return  emptyList()
            }else{
                return listOf(user, token, expiry)
            }

        }
/*
        fun cleanUserPreferences(context: Context){
            preferences = context.getSharedPreferences(USUARIOS_PREFS, Context.MODE_PRIVATE)
            /*preferences.edit()
                .putInt(ID,0)
                .putString(NOMBRE, "")
                .putString(APELLIDOS, "")
                .putString(EMAIL, "")
                .putString(PASSWORD, "")
                .apply()*/
            val editor = preferences.edit()
            editor.clear()
            editor.apply()

            val name = preferences.getString(NOMBRE,"")
            val email = preferences.getString(EMAIL,"")
            Log.i("aa","limpiado")
            Log.i("aa","$name, $email")

        }*/
    }
}