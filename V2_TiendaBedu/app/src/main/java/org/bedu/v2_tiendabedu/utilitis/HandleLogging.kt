package org.bedu.v2_tiendabedu.utilitis

import org.bedu.v2_tiendabedu.models.user.User
import org.bedu.v2_tiendabedu.models.user.arregloUsuarios
import java.util.regex.Pattern

class HandleLogging {
    companion object {
        fun logging(userEmail: String, passwordUser: String): Pair<Boolean, String> {

            val loginUser = findUser(userEmail)
            var valLog = false
            var msg = "Error logging"
            if(loginUser.isEmpty()){
                valLog = false
                msg = "El usuario no existe"
            }else{
                if (loginUser[0].password == passwordUser) {
                    valLog = true
                    msg = "Logging exitoso"
                } else {
                    valLog = false
                    msg = "Error de contraseña"
                }
            }

            return  valLog to msg
        }

        fun fieldsValidate(text: CharSequence, tipo: String): Boolean {

            val patterVal = when (tipo){

                "NAMES" -> VALNAMES
                "EMAIL" -> VALEMAIL
                "PASSWORD" -> VALPASSWORD
                "ALPHANUMERIC"-> VALALPHANUM
                "SERIALNUM"-> VALSERIALNUM

                else -> "fa#%#"


            }
            return Pattern.compile(patterVal).matcher(text).matches()
        }

        private fun findUser(userEmail: String): List<User> {

            return arregloUsuarios.filter { User -> User.email == userEmail }
        }

        fun findUserVal(emailText: String): Pair<Boolean, String>{
            var valFind = false
            var msg = "Error en la búsqueda"
            val userFinded = findUser(emailText)
            if(userFinded.isEmpty()){
                msg = "Usuario creado"
                valFind = true
            }else if ("admin@bedu.com" == userFinded[0].email.lowercase()){
                msg = "El usuario admin es único"
                valFind = false

            }else{
                msg = "El usuario ya existe"
                valFind = false
            }


            return  valFind to msg

        }

        fun setPassword(emailText: String, newPassword:String): Pair<Boolean, String>{
            var valSet = false
            var msg = "Error en la búsqueda"
            val userFinded = findUser(emailText)

            if(userFinded.isEmpty()){
                msg = "Usuario no existe"
                valSet = false
            }else if ("admin@bedu.com" == userFinded[0].email.lowercase()){
                msg = "Contraseña no modificable"
                valSet = false

            }else if(userFinded.isNotEmpty()){
                userFinded[0].password = newPassword
                msg = "Contraseña actualizada"
                valSet = true
            }


            return  valSet to msg

        }




    }
}