package org.bedu.v2_tiendabedu.activities.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.bedu.v2_tiendabedu.MenuActivity
import org.bedu.v2_tiendabedu.R
import org.bedu.v2_tiendabedu.models.user.ResponseLogin
import org.bedu.v2_tiendabedu.models.user.UserLogin
import org.bedu.v2_tiendabedu.utilitis.ErrorMessage
import org.bedu.v2_tiendabedu.utilitis.TiendaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    var status: Boolean= false
    var msg: Any= ""
    var code: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
       /*
        if (LOGGINGCONT == 0){
            val admin = User(
                nombre = "Admin",
                apellidos = "Usuario Adminstrado",
                email = "admin@bedu.com",
                password = "password"
            )
            admin.nombre
        }

        LOGGINGCONT++ */
        val inputUserName: EditText = findViewById(R.id.txtEmail)
        val inputPassword: EditText = findViewById(R.id.txtPass)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val loginErrorMsg: TextView = findViewById(R.id.login_error)
        val lblResetPassword:TextView = findViewById(R.id.reset_password)


        btnLogin.setOnClickListener{
            /*val (valLog, msg) = HandleLogging.logging(inputEmail.text.toString(),
                inputPassword.text.toString())*/
            fun login(status:Boolean, code:Int, msg:Any){
                Log.d("Meeee", "$code $msg $status")
                if (status){
                    val intent = Intent(this, MenuActivity::class.java)
                    // start your next activity
                    startActivity(intent)

                } else loginErrorMsg.text = msg as CharSequence?
            }

            val userLogin = UserLogin(inputUserName.text.toString(),
               inputPassword.text.toString())
            val call_login = TiendaService()
            call_login.apiService.postLoginUser(userLogin).enqueue(object :
                Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    code = response.code()
                    status = response.isSuccessful
                    msg = if (!response.isSuccessful) {
                        ErrorMessage.messege_error(response)

                    }else{
                        response.body()!!
                    }

                    login(status, code, msg)

                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })




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