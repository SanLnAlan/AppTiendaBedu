package org.bedu.v2_tiendabedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import org.bedu.v2_tiendabedu.EditEmailFragment.EditEmailDialogListener
import org.bedu.v2_tiendabedu.EditNameFragment.EditNameDialogListener
import org.bedu.v2_tiendabedu.EditLastnameFragment.EditLastnameDialogListener
import org.bedu.v2_tiendabedu.EditPasswordFragment.EditPasswordDialogListener
import org.bedu.v2_tiendabedu.models.user.User
import org.bedu.v2_tiendabedu.models.user.arregloUsuarios
import org.bedu.v2_tiendabedu.utilitis.HandleLogging
import org.bedu.v2_tiendabedu.utilitis.USERLOGGING


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

        nameText.text = userLogged[0].nombre
        lastnameText.text = userLogged[0].apellidos
        emailText.text = userLogged[0].email
        buttonName.setOnClickListener {showEdit("fragment_edit_name")}
        buttonLastname.setOnClickListener {showEdit("fragment_edit_lastname")}
        buttonEmail.setOnClickListener {showEdit("fragment_edit_email")}
        buttonPassword.setOnClickListener {showEdit("fragment_edit_password")}
    }

    private fun showEdit(fragment_tag: String){
        val fragmentManager = supportFragmentManager
        when(fragment_tag){
            "fragment_edit_name"-> EditNameFragment.newInstance("title").show(fragmentManager, fragment_tag)
            "fragment_edit_lastname"-> EditLastnameFragment.newInstance("title").show(fragmentManager, fragment_tag)
            "fragment_edit_email"-> EditEmailFragment.newInstance("title").show(fragmentManager, fragment_tag)
            "fragment_edit_password"-> EditPasswordFragment.newInstance("title").show(fragmentManager, fragment_tag)
        }
    }


    // 3. This method is invoked in the activity when the listener is triggered
    // Access the data result passed to the activity here
    override fun onFinishEditDialogN(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.name)
        textName.text = inputText
        userLogged[0].nombre = inputText
        Toast.makeText(this, "Nombre cambiado exitosamente", Toast.LENGTH_SHORT).show()
    }

    override fun onFinishEditDialogLn(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.lastname)
        textName.text = inputText
        userLogged[0].apellidos = inputText
        Toast.makeText(this, "Apellido cambiado exitosamente", Toast.LENGTH_SHORT).show()
    }

    override fun onFinishEditDialogE(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.email)
        textName.text = inputText
        userLogged[0].email = inputText
        Toast.makeText(this, "Correo cambiado exitosamente", Toast.LENGTH_SHORT).show()
    }

    override fun onFinishEditDialogP(inputText: String) {
        userLogged[0].password = inputText
        Toast.makeText(this, "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show()
    }
}