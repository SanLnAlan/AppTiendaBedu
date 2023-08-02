package org.bedu.v2_tiendabedu.activities.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import org.bedu.v2_tiendabedu.*
import org.bedu.v2_tiendabedu.activities.user.EditEmailFragment.EditEmailDialogListener
import org.bedu.v2_tiendabedu.activities.user.EditNameFragment.EditNameDialogListener
import org.bedu.v2_tiendabedu.activities.user.EditLastnameFragment.EditLastnameDialogListener
import org.bedu.v2_tiendabedu.activities.user.EditPasswordFragment.EditPasswordDialogListener
import org.bedu.v2_tiendabedu.models.user.ResponseUser
import org.bedu.v2_tiendabedu.utilitis.*
import org.bedu.v2_tiendabedu.utilitis.SharedPrfs.Companion.updateEmail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AccountDetailsActivity : AppCompatActivity(),
    EditNameDialogListener, EditLastnameDialogListener, EditEmailDialogListener, EditPasswordDialogListener {

    private lateinit var buttonName: ImageButton
    private lateinit var buttonLastname: ImageButton
    private lateinit var buttonEmail: ImageButton
    private lateinit var buttonPassword: ImageButton
    private lateinit var nameText: TextView
    private lateinit var lastnameText: TextView
    private lateinit var emailText: TextView
    var userLogged = HandleLogging.findUser(USERLOGGING)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_details)



        //    userLogging[0].id
        buttonName = findViewById(R.id.nameButton)
        buttonLastname = findViewById(R.id.lastnameButton)
        buttonEmail = findViewById(R.id.emailButton)
        buttonPassword = findViewById(R.id.passwordButton)
        nameText = findViewById(R.id.name)
        lastnameText = findViewById(R.id.lastname)
        emailText = findViewById(R.id.email)

        try {
            val (apiDetails, user) = TiendaService.serviceToken(this)
            apiDetails.getUserDetail(email = user!!).enqueue(object : Callback<ResponseUser> {
                override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                    if (!response.isSuccessful) {
                        ErrorMessage.messege_error(response)
                    }else{
                        nameText.text = response.body()?.nombres ?: ""
                        lastnameText.text = response.body()?.apellidos ?: ""
                        emailText.text = response.body()?.email ?: ""
                    }
                    Toast.makeText(this@AccountDetailsActivity, if (response.code()==200)
                        "Perfil del usuario" else "Error", Toast.LENGTH_LONG).show()
                }
                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Toast.makeText(this@AccountDetailsActivity,"Error de conexión.",Toast.LENGTH_SHORT).show()
                }
            })
        } catch (er: Exception){
            Log.e("retrofit",er.toString())
        }
        buttonName.setOnClickListener {showEdit("fragment_edit_name")}
        buttonLastname.setOnClickListener {showEdit("fragment_edit_lastname")}
        buttonEmail.setOnClickListener {showEdit("fragment_edit_email")}
        buttonPassword.setOnClickListener {showEdit("fragment_edit_password")}
    }

    private fun showEdit(fragment_tag: String){
        val fragmentManager = supportFragmentManager
        when(fragment_tag){
            "fragment_edit_name"-> EditNameFragment.newInstance("title")
                .show(fragmentManager, fragment_tag)
            "fragment_edit_lastname"-> EditLastnameFragment.newInstance("title")
                .show(fragmentManager, fragment_tag)
            "fragment_edit_email"-> EditEmailFragment.newInstance("title")
                .show(fragmentManager, fragment_tag)
            "fragment_edit_password"-> EditPasswordFragment.newInstance("title")
                .show(fragmentManager, fragment_tag)
        }
    }


    // 3. This method is invoked in the activity when the listener is triggered
    // Access the data result passed to the activity here
    override fun onFinishEditDialogN(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.name)
        textName.text = inputText
        TiendaService.chanceName(this,inputText)
    }

    override fun onFinishEditDialogLn(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.lastname)
        textName.text = inputText
        TiendaService.chanceSurname(this,inputText)
    }

    override fun onFinishEditDialogE(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.email)
        textName.text = inputText
        TiendaService.chanceEmail(this,inputText)
        updateEmail(inputText, this)
    }

    override fun onFinishEditDialogP(inputText: String, inputText2: String) {
        TiendaService.chancePassword(this,inputText, inputText2)
        TiendaService.logout(this)
        SharedPrfs.cleanUserPreferences(this)
        finishAffinity()
    }
}