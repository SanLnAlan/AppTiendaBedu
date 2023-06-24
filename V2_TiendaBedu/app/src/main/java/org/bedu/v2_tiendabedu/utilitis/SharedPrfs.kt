package org.bedu.v2_tiendabedu.utilitis

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.bedu.v2_tiendabedu.models.user.User

class SharedPrfs {

    companion object{

        private const val USUARIOS_PREFS = "usuarios_prefs"
        private const val ID = "id"
        private const val NOMBRE = "nombre"
        private const val APELLIDOS = "apellidos"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
        private lateinit var preferences: SharedPreferences


        fun saveUserPreferences(user: User, context: Context){
            preferences = context.getSharedPreferences(USUARIOS_PREFS, Context.MODE_PRIVATE)
            preferences.edit()
                .putInt(ID,user.id)
                .putString(NOMBRE, user.nombre)
                .putString(APELLIDOS, user.apellidos)
                .putString(EMAIL, user.email)
                .putString(PASSWORD, user.password)
                .apply()
        }

        fun getUserPreferences(context: Context): List<String?> {
            preferences = context.getSharedPreferences(USUARIOS_PREFS, Context.MODE_PRIVATE)
            val name = preferences.getString(NOMBRE,"")
            val email = preferences.getString(EMAIL,"")
            return listOf(name,email)
        }

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

        }
    }
}