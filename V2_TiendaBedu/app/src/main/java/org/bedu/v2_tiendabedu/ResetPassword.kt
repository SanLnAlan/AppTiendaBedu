package org.bedu.v2_tiendabedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.bedu.v2_tiendabedu.utilitis.ERRORPASSWORD
import org.bedu.v2_tiendabedu.utilitis.HandleLogging
import org.bedu.v2_tiendabedu.utilitis.MESSGPASSWORD
class ResetPassword : AppCompatActivity() {
    var valEmail = false
    var valPassword = false
    var valPasswordConf = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val btnReset: Button = findViewById(R.id.btnReset)
        val inputEmail: EditText = findViewById(R.id.txtEmail)
        val registerErrorMsg: TextView = findViewById(R.id.register_error)
        val inputPassword: EditText = findViewById(R.id.txtPass)
        val inputPasswordConf: EditText = findViewById(R.id.txtPassConf)
        val lblGotoLogging: TextView = findViewById(R.id.link_to_login)

        inputEmail.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {
                    if (HandleLogging.fieldsValidate(s, "EMAIL")){

                        "Email OK".also { registerErrorMsg.text = it }
                        valEmail = true
                        if (activateButton()){
                            btnReset.isEnabled = true
                        }
                    }
                    else{
                        "Error en el email".also { registerErrorMsg.text = it }
                        valEmail = false
                        btnReset.isEnabled = false
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
                if (HandleLogging.fieldsValidate(s, "PASSWORD")){

                    "Password OK".also { registerErrorMsg.text = it }
                    valPassword = true
                    if (activateButton()){
                        btnReset.isEnabled = true
                    }
                }
                else{
                    registerErrorMsg.text = MESSGPASSWORD
                    valPassword = false
                    btnReset.isEnabled = false
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
                if (HandleLogging.fieldsValidate(s, "PASSWORD")){

                    "Password OK".also { registerErrorMsg.text = it }
                    valPasswordConf = true
                    if (activateButton()){
                        btnReset.isEnabled = true
                    }
                }
                else{
                    registerErrorMsg.text = MESSGPASSWORD
                    valPasswordConf = false
                    btnReset.isEnabled = false
                }

            }
        })

        lblGotoLogging.setOnClickListener{

            val intent = Intent(this, LoginActivity::class.java)
            // start your next activity
            startActivity(intent)

        }

        btnReset.setOnClickListener{
            if(inputPassword.text.toString() == inputPasswordConf.text.toString()){

                val (valUser, msgUser)= HandleLogging.setPassword(inputEmail.text.toString(),
                    inputPassword.text.toString())

                if(valUser){

                    inputEmail.setText("")
                    inputPassword.setText("")
                    inputPasswordConf.setText("")
                    btnReset.isEnabled = false
                    registerErrorMsg.text = msgUser

                }else{
                    msgUser.also { registerErrorMsg.text = it }
                    valEmail = false
                    valPassword = false
                    valPasswordConf = false
                    btnReset.isEnabled = false
                }


            }else{
                ERRORPASSWORD.also { registerErrorMsg.text = it }
                btnReset.isEnabled = false
            }
        }



    }

    private fun activateButton(): Boolean {
        var v = false
        if (valEmail && valPassword && valPasswordConf) {
            v = true
        }
        return v
    }
}