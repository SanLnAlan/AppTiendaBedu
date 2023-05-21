package org.bedu.v2_tiendabedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import org.bedu.v2_tiendabedu.EditNameFragment.EditNameDialogListener
import org.bedu.v2_tiendabedu.models.user.arregloUsuarios

class AccountDetailsActivity : AppCompatActivity(), EditNameDialogListener {

    private lateinit var buttonName: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_details)

        buttonName = findViewById(R.id.nameButton)

        buttonName.setOnClickListener {
            showEditName()
        }
    }

    private fun showEditName() {
        val fragmentManager = supportFragmentManager
        val editNameFragment = EditNameFragment.newInstance("title")
        editNameFragment.show(fragmentManager, "fragment_edit_name")
    }

    // 3. This method is invoked in the activity when the listener is triggered
    // Access the data result passed to the activity here
    override fun onFinishEditDialog(inputText: String) {
        val textName: TextView
        textName = findViewById(R.id.name)
        textName.text = inputText
        Toast.makeText(this, "Hi, " + inputText, Toast.LENGTH_SHORT).show();
    }
}