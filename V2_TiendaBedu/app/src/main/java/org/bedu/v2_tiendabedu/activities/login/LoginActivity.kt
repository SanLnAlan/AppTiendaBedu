package org.bedu.v2_tiendabedu.activities.login

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.bedu.v2_tiendabedu.MainActivity
import org.bedu.v2_tiendabedu.MenuActivity
import org.bedu.v2_tiendabedu.R
import org.bedu.v2_tiendabedu.models.user.ResponseLogin
import org.bedu.v2_tiendabedu.models.user.UserLogin
import org.bedu.v2_tiendabedu.utilitis.ErrorMessage
import org.bedu.v2_tiendabedu.utilitis.SharedPrfs.Companion.getUserPreferences
import org.bedu.v2_tiendabedu.utilitis.SharedPrfs.Companion.saveUserPreferences
import org.bedu.v2_tiendabedu.utilitis.TiendaService
import org.bedu.v2_tiendabedu.utilitis.Utils
import org.bedu.v2_tiendabedu.utilitis.Utils.Companion.checkSelfPermissionCompat
import org.bedu.v2_tiendabedu.utilitis.Utils.Companion.requestPermissionsCompat
import org.bedu.v2_tiendabedu.utilitis.Utils.Companion.shouldShowRequestPermissionRationaleCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.bedu.v2_tiendabedu.utilitis.DownloadController



class LoginActivity : AppCompatActivity() {
    var status: Boolean= false
    var msg: Any= ""
    var code: Int =0

    companion object {
        const val PERMISSION_REQUEST_STORAGE = 0
        const val urlApp = "https://github.com/Marant08-2020/testdeploy/raw/main/app-release.apk"
        const val urlCode = "https://github.com/Marant08-2020/testdeploy/raw/main/versionCode.txt"
    }
    private lateinit var downloadController: DownloadController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        val inputUserName: EditText = findViewById(R.id.txtEmail)
        val inputPassword: EditText = findViewById(R.id.txtPass)
        val btnLogin: Button = findViewById(R.id.btnLogin)
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
        downloadController = DownloadController(this, urlApp)
        checkUpdate()

        btnLogin.setOnClickListener{

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
                    TODO("Not yet implemented")
                }
            })




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




    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LoginActivity.PERMISSION_REQUEST_STORAGE) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadController.enqueueDownload()
            } else {
                Toast.makeText(this, R.string.storage_permission_denied, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkStoragePermission() {

        if (checkSelfPermissionCompat(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        ) {
            downloadController.enqueueDownload()
        }else if(Build.VERSION.SDK_INT >= 29){
            downloadController.enqueueDownload()
        }
        else {
            requestStoragePermission()
        }
    }

    private fun requestStoragePermission() {
        if (shouldShowRequestPermissionRationaleCompat(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, R.string.storage_permission_denied, Toast.LENGTH_LONG).show()
            requestPermissionsCompat(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                LoginActivity.PERMISSION_REQUEST_STORAGE
            )
        } else {
            requestPermissionsCompat(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                LoginActivity.PERMISSION_REQUEST_STORAGE
            )
        }
    }


    private fun checkUpdate(){
        Thread {
            val remoteVersionCode = Utils.readUrlFile(urlCode)
            if (remoteVersionCode !== null) {

                val localVersionCode: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                    packageManager.getPackageInfo(packageName, 0).longVersionCode.toInt()
                } else {
                    packageManager.getPackageInfo(packageName, 0).versionCode

                }

                Log.e("TAG", "onCreate: $remoteVersionCode, $localVersionCode")
                runOnUiThread {
                    if (remoteVersionCode.toInt() > localVersionCode) {
                        val alertDialog: AlertDialog = Utils.buildAlertDialog(
                            this,
                            R.string.new_version,
                            R.string.new_version_msg
                        )
                        alertDialog.setButton(
                            DialogInterface.BUTTON_POSITIVE, getString(R.string.btn_update)
                        ) { dialog: DialogInterface, _: Int ->
                            dialog.dismiss()
                            checkStoragePermission()
                        }
                        alertDialog.setButton(
                            DialogInterface.BUTTON_NEGATIVE, getString(R.string.btn_cancel)
                        ) { dialog: DialogInterface, _: Int ->
                            dialog.dismiss()
                        }
                        alertDialog.show()
                    }
                }
            } else {
                Log.e("TAG", "onCreate: error checking version")
            }
        }.start()
    }

}