package org.bedu.v2_tiendabedu

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this@MyApp)
        Log.e("inicio","firebase inicilizaod")
    }
}