package org.bedu.v2_tiendabedu.utilitis

import org.bedu.v2_tiendabedu.models.user.RegisterUser
import org.bedu.v2_tiendabedu.models.user.ResponseLogin
import org.bedu.v2_tiendabedu.models.user.ResponseUser
import org.bedu.v2_tiendabedu.models.user.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login/")
    fun postLoginUser(@Body userLogin: UserLogin): Call<ResponseLogin>

    @POST("register_user/")
    fun postRegisterUser(@Body registerUser: RegisterUser): Call<ResponseUser>
}