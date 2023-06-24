package org.bedu.v2_tiendabedu.models.user

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

    @SerializedName("expiry") var expiry: String,
    @SerializedName("token") var token: String,
    @SerializedName("user") var user: UserDetail,

    )

data class UserDetail(

    @SerializedName("username") var username: String,
)

data class UserLogin(
    @SerializedName("username") var username: String,
    @SerializedName("password") var password: String,
)

data class RegisterUser(
    @SerializedName("username") var username: String,
    @SerializedName("email") var email: String,
    @SerializedName("first_name") var nombres: String,
    @SerializedName("last_name") var apellidos: String,
    @SerializedName("password") var password: String,
    @SerializedName("password2") var password2: String,
)

data class ResponseUser(
    @SerializedName("username") var username: String,
    @SerializedName("email") var email: String,
    @SerializedName("first_name") var nombres: String,
    @SerializedName("last_name") var apellidos: String,
)

