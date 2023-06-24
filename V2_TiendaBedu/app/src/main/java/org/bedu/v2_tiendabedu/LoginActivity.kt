package org.bedu.v2_tiendabedu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.bedu.v2_tiendabedu.models.user.User
import org.bedu.v2_tiendabedu.utilitis.HandleLogging
import org.bedu.v2_tiendabedu.utilitis.LOGGINGCONT
import org.bedu.v2_tiendabedu.utilitis.SharedPrfs.Companion.getUserPreferences
import org.bedu.v2_tiendabedu.utilitis.SharedPrfs.Companion.saveUserPreferences
import kotlin.math.log


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getLog = getUserPreferences(this)
        if (getLog[0] != ""){
            Log.d("ususariodintisitno","${getLog}")
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        } else {
            Log.d("NO ESTA NULO: ","${getLog}")
            setContentView(R.layout.activity_login)

            if (LOGGINGCONT == 0){
                val admin = User(
                    nombre = "Admin",
                    apellidos = "Usuario Adminstrado",
                    email = "admin@bedu.com",
                    password = "password"
                )
                admin.nombre
            }

            LOGGINGCONT++
            val inputEmail: EditText = findViewById(R.id.txtEmail)
            val inputPassword: EditText = findViewById(R.id.txtPass)
            val btnLogin: Button = findViewById(R.id.btnLogin)
            val loginErrorMsg: TextView = findViewById(R.id.login_error)
            val lblResetPassword:TextView = findViewById(R.id.reset_password)

            btnLogin.setOnClickListener{
                // descomentar this
                //val (valLog, msg) = HandleLogging.logging(inputEmail.text.toString(),
                //    inputPassword.text.toString())

                val logData = HandleLogging.logging(inputEmail.text.toString(),
                    inputPassword.text.toString())

                val valLog = logData[0] as Boolean
                val msg = logData[1] as String
                val user = logData[2] as? User

                //delete this
//            val valLog = true
//            val msg = "true"

                if (valLog){
                    val intent = Intent(this, MenuActivity::class.java)
                    // start your next activity
                    startActivity(intent)
                    if (user != null) {
                        saveUserPreferences(user,this)
                        getUserPreferences(this)
                    }
                } else loginErrorMsg.text = msg

            }
            val lblGotoRegister: TextView = findViewById(R.id.link_to_register)

            lblGotoRegister.setOnClickListener{

                val intent = Intent(this, RegisterUserActivity::class.java)
                startActivity(intent)
            }

            lblResetPassword.setOnClickListener{

                val intent = Intent(this, ResetPassword::class.java)
                startActivity(intent)
            }
        }

    }

}