package org.bedu.v2_tiendabedu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.bedu.v2_tiendabedu.models.user.User
import org.bedu.v2_tiendabedu.utilitis.HandleLogging
import org.bedu.v2_tiendabedu.utilitis.LOGGINGCONT





class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            /*val (valLog, msg) = HandleLogging.logging(inputEmail.text.toString(),
                inputPassword.text.toString())*/

            //delete this
            val valLog = true
            val msg = "true"

            if (valLog){
                val intent = Intent(this, MenuActivity::class.java)
                // start your next activity
                startActivity(intent)
            } else loginErrorMsg.text = msg

        }
        val lblGotoRegister: TextView = findViewById(R.id.link_to_register)

        lblGotoRegister.setOnClickListener{

            val intent = Intent(this, RegisterUserActivity::class.java)
            startActivity(intent)
        }

        lblResetPassword.setOnClickListener{

            val intent = Intent(this, ResetPassword::class.java)
            // start your next activity
            startActivity(intent)

        }

    }

}