package org.bedu.v2_tiendabedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import org.bedu.v2_tiendabedu.EditEmailFragment.EditEmailDialogListener
import org.bedu.v2_tiendabedu.EditNameFragment.EditNameDialogListener
import org.bedu.v2_tiendabedu.EditLastnameFragment.EditLastnameDialogListener
import org.bedu.v2_tiendabedu.models.user.arregloUsuarios

class AccountDetailsActivity : AppCompatActivity(), EditNameDialogListener, EditLastnameDialogListener, EditEmailDialogListener {

    private lateinit var buttonName: ImageButton
    private lateinit var buttonLastname: ImageButton
    private lateinit var buttonEmail: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_details)

        buttonName = findViewById(R.id.nameButton)
        buttonLastname = findViewById(R.id.lastnameButton)
        buttonEmail = findViewById(R.id.emailButton)

        buttonName.setOnClickListener {showEditName()}
        buttonLastname.setOnClickListener{ showEditLastname()}
        buttonEmail.setOnClickListener{ showEditEmail() }
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

    // 3. This method is invoked in the activity when the listener is triggered
    // Access the data result passed to the activity here
    override fun onFinishEditDialogN(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.name)
        textName.text = inputText
        Toast.makeText(this, "Nombre cambiado correctamente", Toast.LENGTH_SHORT).show();
    }

    override fun onFinishEditDialogLn(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.lastname)
        textName.text = inputText
        Toast.makeText(this, "Apellido cambiado correctamente", Toast.LENGTH_SHORT).show();
    }

    override fun onFinishEditDialogE(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.email)
        textName.text = inputText
        Toast.makeText(this, "Correo cambiado exitosamente", Toast.LENGTH_SHORT).show();
    }
}