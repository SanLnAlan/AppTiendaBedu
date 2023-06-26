package org.bedu.v2_tiendabedu.utilitis

import org.bedu.v2_tiendabedu.models.user.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("login/")
    fun postLoginUser(@Body userLogin: UserLogin): Call<ResponseLogin>

    @POST("register_user/")
    fun postRegisterUser(@Body registerUser: RegisterUser): Call<ResponseUser>

    @GET("details_user/{email}")
    fun getUserDetail(@Path("email") email:String): Call<ResponseUser>

    @PUT("chance_name/{email}/")
    fun putNameUser(@Path("email") email:String, @Body chanceName: ChangeName): Call<Unit>

    @PUT("chance_surname/{email}/")
    fun putSurnameUser(@Path("email") email:String, @Body chanceSurname: ChangeSurname): Call<Unit>

    @PUT("chance_email/{email}/")
    fun putEmailUser(@Path("email") email:String, @Body changeEmail: ChangeEmail): Call<Unit>

    @PUT("chance_password/{email}/")
    fun putPasswordlUser(@Path("email") email:String, @Body chancePassword: ChancePassword): Call<Unit>

    @POST("logout/")
    fun postLogoutUser():Call<Unit>
}