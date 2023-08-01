package org.bedu.v2_tiendabedu.utilitis

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Utils {

    companion object {
    fun AppCompatActivity.checkSelfPermissionCompat(permission: String) =
        ActivityCompat.checkSelfPermission(this, permission)

    fun AppCompatActivity.shouldShowRequestPermissionRationaleCompat(permission: String) =
        ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

    fun AppCompatActivity.requestPermissionsCompat(
        permissionsArray: Array<String>,
        requestCode: Int
    ) {
        ActivityCompat.requestPermissions(this, permissionsArray, requestCode)
    }
        fun buildAlertDialog(context: Context, resTitle: Int, resMessage: Int): AlertDialog {
            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setTitle(context.getString(resTitle))
            alertDialog.setMessage(context.getString(resMessage))
            alertDialog.setCancelable(false)
            return alertDialog
        }
        fun readUrlFile(url: String): String? {
            var data: String? = null
            var iStream: InputStream? = null
            var urlConnection: HttpURLConnection? = null
            try {
                urlConnection = URL(url).openConnection() as HttpURLConnection?
                urlConnection?.connect()
                iStream = urlConnection?.inputStream
                val br = BufferedReader(InputStreamReader(iStream))
                val sb = StringBuilder()
                var line: String?
                while (br.readLine().also { line = it } != null) {
                    sb.append(line)
                }
                data = sb.toString()
                br.close()
            } catch (e: Exception) {
                Log.w("", "Exception while downloading url: $e")
            } finally {
                iStream?.close()
                urlConnection?.disconnect()
            }
            return data
        }

    }


}