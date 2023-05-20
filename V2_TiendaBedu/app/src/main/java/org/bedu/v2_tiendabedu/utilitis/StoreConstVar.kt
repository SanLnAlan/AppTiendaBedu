package org.bedu.v2_tiendabedu.utilitis

var LOGGINGCONT: Int = 0
const val VALNAMES: String = "^([A-Za-zÑñÁáÉéÍíÓóÚú]+['\\-]{0,1}[A-Za-zÑñÁáÉéÍíÓóÚú]+)(\\s{0,1}([A-Za-zÑñÁáÉéÍíÓóÚú]+['\\-]{0,1}[A-Za-zÑñÁáÉéÍíÓóÚú]+)){0,1}\$"
const val VALEMAIL: String = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})\$"
const val VALPASSWORD: String = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$"
const val VALALPHANUM: String = "^([a-zA-Z0-9 ._-]){1,20}\$"

// Messages

val MESSGPASSWORD: String = """
El password debe contener  al menos
una mayuscula, una minúscula,un 
digíto,un carácter especial #?!@\$%^&*- de
8 caracteres.""".trimIndent()

const val ERRONAME = "Error nombre"
const val OKNAME = "Nombre correcto"
const val SURNAMEOK = "Apellidos correcto"
const val ERRORSURNAME = "Error apellidos"
const val EMAILEOK = "Email correcto"
const val ERROREMAIL = "Error email"
const val PASSWORDOK = "Contraseña correcto"
const val ERRORPASSWORD = "Contreseñas no coinciden"
const val PASSWORDOkCONF = "Contraseña confirmada correcto"
const val OKDESCR = "Error descripción del producto"
const val ERRORDESC = "Descripción del producto correcto"

