package org.bedu.v2_tiendabedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var iniciarBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciarBtn = findViewById(R.id.iniciar_btn)

        iniciarBtn.setOnClickListener {
            Log.i("tag","abriendo")
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}