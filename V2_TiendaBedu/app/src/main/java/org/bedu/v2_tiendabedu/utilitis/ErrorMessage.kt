package org.bedu.v2_tiendabedu.utilitis

import org.bedu.v2_tiendabedu.models.user.ResponseLogin
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader


class ErrorMessage{
    companion object {

        fun<T> messege_error(response: Response<T> ): String{
            val error = StringBuilder()
            try {
                var bufferedReader: BufferedReader? = null
                if (response.errorBody() != null) {
                    bufferedReader = BufferedReader(
                        InputStreamReader(
                            response.errorBody()!!.byteStream()
                        )
                    )
                    var eLine: String? = null
                    while (bufferedReader.readLine().also { eLine = it } != null) {
                        error.append(eLine)
                    }
                    bufferedReader.close()
                }
            } catch (e: Exception) {
                error.append(e.message)
            }

            return error.toString()
        }
    }
}