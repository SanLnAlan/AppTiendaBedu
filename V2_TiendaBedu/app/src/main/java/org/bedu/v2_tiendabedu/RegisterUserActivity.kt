package org.bedu.v2_tiendabedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.bedu.v2_tiendabedu.models.user.User
import org.bedu.v2_tiendabedu.utilitis.*
import org.bedu.v2_tiendabedu.utilitis.HandleLogging.Companion.fieldsValidate
import org.bedu.v2_tiendabedu.utilitis.HandleLogging.Companion.findUserVal


class RegisterUserActivity : AppCompatActivity() {
    var valName = false
    var valSuname = false
    var valEmail = false
    var valPassword = false
    var valPasswordConf = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

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
                    val user = User(nombre = inputName.text.toString(),
                        apellidos = inputSurname.text.toString(),
                        email = inputEmail.text.toString(),
                        password = inputPassword.text.toString())
                    btnRegister.isEnabled = false
                    inputName.setText("")
                    inputSurname.setText("")
                    inputEmail.setText("")
                    inputPassword.setText("")
                    inputPasswordConf.setText("")

                    user.addUsers(usuario = user)
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

    private fun activateButton(): Boolean {
        var v = false
        if (valName && valSuname && valEmail && valPassword && valPasswordConf) {
            v = true
        }
        return v
    }

}