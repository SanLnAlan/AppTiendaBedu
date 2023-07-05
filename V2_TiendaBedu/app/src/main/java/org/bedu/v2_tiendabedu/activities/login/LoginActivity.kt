package org.bedu.v2_tiendabedu.activities.login

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.bedu.v2_tiendabedu.R
import org.bedu.v2_tiendabedu.databinding.ActivityLoginBinding
import org.bedu.v2_tiendabedu.utilitis.Utils
import org.bedu.v2_tiendabedu.utilitis.Utils.hideKeyboard
import java.lang.Exception
import org.bedu.v2_tiendabedu.MyApp

class LoginActivity : Activity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        handleBtnLogin()
    }

    private fun handleBtnLogin() {
        binding.btnLogin.setOnClickListener {
            it.hideKeyboard()

            binding.btnLogin.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE

            val email = binding.txtEmail.text.toString()
            val password = binding.txtPass.text.toString()

            signIn(email, password)
        }


        binding.txtEmail.doAfterTextChanged {
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPass.text.toString()

            binding.btnLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }

        binding.txtPass.doAfterTextChanged {
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPass.text.toString()

            binding.btnLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                if(it.isSuccessful){
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user, null)
                } else {
                    Log.w(TAG, "signInWithEmail:failure", it.exception)

                }
            }
    }

    private fun updateUI(user: FirebaseUser?, exception: Exception?) {
        if (exception != null){
            Utils.displaySnackBar(binding.root, exception.message.toString(),this,R.color.red)
        } else {
            Utils.displaySnackBar(binding.root, "Inicio de sesión exitoso",this,R.color.green)
        }
        binding.loading.visibility = View.GONE
        binding.btnLogin.visibility = View.VISIBLE
    }

    companion object {
        private const val TAG = "Email"
    }
}