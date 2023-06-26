package org.bedu.v2_tiendabedu.utilitis

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(token: String):Interceptor {
    private var tokenLogin: String
    init {
      tokenLogin = token

    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            ?.addHeader(
                "Authorization", "$tokenLogin"
            )?.build()

        return chain.proceed(request)
    }


}