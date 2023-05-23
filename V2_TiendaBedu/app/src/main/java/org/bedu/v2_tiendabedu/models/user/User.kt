package org.bedu.v2_tiendabedu.models.user

import android.util.Log
import java.util.*
// Variable global de usuarios
var arregloUsuarios = LinkedList<User>()

data class User(
    var id: Int = 0,
    var nombre: String = "Admin",
    var apellidos: String = "Usuario Adminstrado",
    var email: String = "admin@bedu.com",
    var password: String = "password"
) {
    // Variable estática
    companion object {
        private var contadorUser: Int = 0
    }
    init {
        contadorUser += 1

    }
    init {
        this.id = contadorUser
        Log.d("id", "Id-> ${this.id}")
        if(this.id == 1){
            this.addUsers(this)
        }

    }

    fun addUsers(usuario:User){
        arregloUsuarios.addLast(usuario)
    }
}

