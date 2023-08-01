package org.bedu.v2_tiendabedu.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.bedu.v2_tiendabedu.R
import org.bedu.v2_tiendabedu.models.user.RegisterUser
import org.bedu.v2_tiendabedu.models.user.ResponseUser
import org.bedu.v2_tiendabedu.utilitis.*
import org.bedu.v2_tiendabedu.utilitis.HandleLogging.Companion.fieldsValidate
import org.bedu.v2_tiendabedu.utilitis.HandleLogging.Companion.findUserVal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterUserActivity : AppCompatActivity() {
    var valName = false
    var valSuname = false
    var valEmail = false
    var valPassword = false
    var valPasswordConf = false
    var status: Boolean= false
    var msg: Any= ""
    var code: Int =0

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        val btnRegister: Button = findViewById(R.id.btnRegister)
        val inputName:  EditText = findViewById(R.id.txtUserName)
        val inputSurname: EditText = findViewById(R.id.txtUserApellido)
        val inputEmail: EditText = findViewById(R.id.txtEmail)
        val inputPassword: EditText = findViewById(R.id.txtPass)
        val inputPasswordConf: EditText = findViewById(R.id.txtPassConf)
        val registerErrorMsg: TextView = findViewById(R.id.register_error)
        val lblGotoLogging: TextView = findViewById(R.id.link_to_login)

        inputName.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (fieldsValidate(s, "NAMES")){

                    registerErrorMsg.text = OKNAME
                    valName = true
                    if (activateButton()){
                        btnRegister.isEnabled = true
                    }
                }
                else{
                    registerErrorMsg.text = ERRONAME
                    valName = false
                    btnRegister.isEnabled = false
                }

            }
        })

        inputSurname.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (fieldsValidate(s, "NAMES")){

                    registerErrorMsg.text = SURNAMEOK
                    valSuname = true
                    if (activateButton()){
                        btnRegister.isEnabled = true
                    }
                }
                else{
                    registerErrorMsg.text = ERRORSURNAME
                    valSuname = false
                    btnRegister.isEnabled = false
                }

            }
        })

        inputEmail.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (fieldsValidate(s, "EMAIL")){

                    registerErrorMsg.text = EMAILEOK
                    valEmail = true
                    if (activateButton()){
                        btnRegister.isEnabled = true
                    }
                }
                else{
                    registerErrorMsg.text = ERROREMAIL
                    valEmail = false
                    btnRegister.isEnabled = false
                }

            }
        })

            inputPassword.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {
                    if (fieldsValidate(s, "PASSWORD")){

                        registerErrorMsg.text = PASSWORDOK
                        valPassword = true
                        if (activateButton()){
                            btnRegister.isEnabled = true
                        }
                    }
                    else{
                        registerErrorMsg.text = MESSGPASSWORD
                        valPassword = false
                        btnRegister.isEnabled = false
                    }

                }
            })
        inputPasswordConf.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (fieldsValidate(s, "PASSWORD")){

                    registerErrorMsg.text = PASSWORDOkCONF
                    valPasswordConf = true
                    if (activateButton()){
                        btnRegister.isEnabled = true
                    }

                }
                else{
                    registerErrorMsg.text = MESSGPASSWORD
                    valPasswordConf = false
                    btnRegister.isEnabled = false
                }

            }
        })

        lblGotoLogging.setOnClickListener{

            val intent = Intent(this, LoginActivity::class.java)
            // start your next activity
            startActivity(intent)

        }
        btnRegister.setOnClickListener{



            if(inputPassword.text.toString() == inputPasswordConf.text.toString()){
                val (valUser, msgUser)= findUserVal(inputEmail.text.toString())
                if (valUser){
                    createAccountWithFirebase(inputEmail.text.toString(), inputPassword.text.toString())
                    val user = RegisterUser(
                        username=inputEmail.text.toString(),
                        nombres = inputName.text.toString(),
                        apellidos = inputSurname.text.toString(),
                        email = inputEmail.text.toString(),
                        password = inputPassword.text.toString(),
                        password2 = inputPasswordConf.text.toString())
                    val callRegister = TiendaService()
                    callRegister.apiService.postRegisterUser(user).enqueue(object :
                        Callback<ResponseUser>{
                        override fun onResponse(
                            call: Call<ResponseUser>,
                            response: Response<ResponseUser>
                        ) {
                            code = response.code()
                            status = response.isSuccessful
                            msg = if (!response.isSuccessful) {
                                ErrorMessage.messege_error(response)

                            }else{
                                response.message()!!
                            }
                            registerErrorMsg.text = msg.toString()

                        }

                        override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                            Log.w("Retrofit","Error al registrar usuario")
                        }

                    })
                    btnRegister.isEnabled = false
                    inputName.setText("")
                    inputSurname.setText("")
                    inputEmail.setText("")
                    inputPassword.setText("")
                    inputPasswordConf.setText("")


                    //user.addUsers(usuario = user)
                    registerErrorMsg.text = msgUser
                }else{
                    registerErrorMsg.text = msgUser
                    btnRegister.isEnabled = false
                }
            }else {
                registerErrorMsg.text = ERRORPASSWORD
                btnRegister.isEnabled = false
            }
        }

    }

    private fun createAccountWithFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Log.d("registro","createAccount: success")
                } else {
                    Log.w("registro","createAccount: failure", it.exception)
                }
            }
    }

    private fun activateButton(): Boolean {
        var v = false
        if (valName && valSuname && valEmail && valPassword && valPasswordConf) {
            v = true
        }
        return v
    }

}