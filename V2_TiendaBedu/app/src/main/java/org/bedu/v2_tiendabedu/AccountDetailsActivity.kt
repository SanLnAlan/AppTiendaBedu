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
        buttonName.setOnClickListener {showEditName()}
        buttonLastname.setOnClickListener {showEditLastname()}
        buttonEmail.setOnClickListener {showEditEmail()}
        buttonPassword.setOnClickListener {showEditPassword()}
    }

    private fun showEditName() {
        val fragmentManager = supportFragmentManager
        val editNameFragment = EditNameFragment.newInstance("title")
        editNameFragment.show(fragmentManager, "fragment_edit_name")
    }

    private fun showEditLastname() {
        val fragmentManager = supportFragmentManager
        val editLastnameFragment = EditLastnameFragment.newInstance("title")
        editLastnameFragment.show(fragmentManager, "fragment_edit_lastname")
    }

    private fun showEditEmail() {
        val fragmentManager = supportFragmentManager
        val editEmailFragment = EditEmailFragment.newInstance("title")
        editEmailFragment.show(fragmentManager, "fragment_edit_email")
    }

    private fun showEditPassword() {
        val fragmentManager = supportFragmentManager
        val editPasswordFragment = EditPasswordFragment.newInstance("title")
        editPasswordFragment.show(fragmentManager, "fragment_edit_password")
    }

    // 3. This method is invoked in the activity when the listener is triggered
    // Access the data result passed to the activity here
    override fun onFinishEditDialogN(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.name)
        textName.text = inputText
        userLogged[0].nombre = inputText
        Toast.makeText(this, "Nombre cambiado exitosamente", Toast.LENGTH_SHORT).show();
    }

    override fun onFinishEditDialogLn(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.lastname)
        textName.text = inputText
        userLogged[0].apellidos = inputText
        Toast.makeText(this, "Apellido cambiado exitosamente", Toast.LENGTH_SHORT).show();
    }

    override fun onFinishEditDialogE(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.email)
        textName.text = inputText
        userLogged[0].email = inputText
        Toast.makeText(this, "Correo cambiado exitosamente", Toast.LENGTH_SHORT).show();
    }

    override fun onFinishEditDialogP(inputText: String) {
        userLogged[0].password = inputText
        Toast.makeText(this, "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show();
    }
}