package org.bedu.v2_tiendabedu.activities.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.bedu.v2_tiendabedu.MenuActivity
import org.bedu.v2_tiendabedu.R
import org.bedu.v2_tiendabedu.models.user.ResponseLogin
import org.bedu.v2_tiendabedu.models.user.UserLogin
import org.bedu.v2_tiendabedu.utilitis.ErrorMessage
import org.bedu.v2_tiendabedu.utilitis.SharedPrfs.Companion.getUserPreferences
import org.bedu.v2_tiendabedu.utilitis.SharedPrfs.Companion.saveUserPreferences
import org.bedu.v2_tiendabedu.utilitis.SharedPrfs.Companion.saveUserPreferencesFromGoogle
import org.bedu.v2_tiendabedu.utilitis.TiendaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    var status: Boolean= false
    var msg: Any= ""
    var code: Int =0
    private val GOOGLE_SIGN_IN = 100

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        val inputUserName: EditText = findViewById(R.id.txtEmail)
        val inputPassword: EditText = findViewById(R.id.txtPass)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnGoogle: Button = findViewById(R.id.btnLoginGoogle)
        val loginErrorMsg: TextView = findViewById(R.id.login_error)
        val lblResetPassword:TextView = findViewById(R.id.reset_password)
        val lisP = getUserPreferences(this)

        fun login(status:Boolean, code:Int, msg:Any){

            if (status || lisP.isNotEmpty()){
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)

            } else loginErrorMsg.text = msg as CharSequence?
        }

        login(false, 0, "")

        btnLogin.setOnClickListener{
            if (inputUserName.text.isNotEmpty() and inputPassword.text.isNotEmpty()){
                loginUserWithFirebase(inputUserName.text.toString(), inputPassword.text.toString())
            }
            val userLogin = UserLogin(inputUserName.text.toString(),
               inputPassword.text.toString())
            val callLogin = TiendaService()
            callLogin.apiService.postLoginUser(userLogin).enqueue(object :
                Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    code = response.code()
                    status = response.isSuccessful
                    msg = if (!response.isSuccessful) {
                        ErrorMessage.messege_error(response)

                    }else{
                        response.body()!!
                        saveUserPreferences(response.body()!!, this@LoginActivity)
                    }

                    login(status, code, msg)

                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    Log.w("Retrofit","Error al registrar usuario")
                }
            })




        }

        btnGoogle.setOnClickListener {
            val googleConf =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }


        val lblGotoRegister: TextView = findViewById(R.id.link_to_register)

        lblGotoRegister.setOnClickListener{

            val intent = Intent(this, RegisterUserActivity::class.java)
            startActivity(intent)
        }

        lblResetPassword.setOnClickListener{

            val intent = Intent(this, ResetPassword::class.java)
            // start your next activity
            startActivity(intent)

        }

    }

    private fun loginUserWithFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Log.d("login","success")
                } else {
                    Log.w("login","failure", it.exception)
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener{
                        if (it.isSuccessful){
                            Log.i("GoogleIN","success")
                            saveUserPreferencesFromGoogle(
                                    account.email.toString(),
                                    account.isExpired.toString(),
                                    account.idToken.toString(),
                                this@LoginActivity)
                            val intent = Intent(this, MenuActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            } catch (e:ApiException){
                Log.e("Google",e.toString())
            }


        }
    }

}