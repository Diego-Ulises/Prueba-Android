package com.example.puebaandroid.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import com.example.puebaandroid.models.DefaultResponse
import com.example.puebaandroid.models.LoginResponse
import retrofit2.http.Header

interface Api {

    @FormUrlEncoded
    @POST("auth/register")
    fun createUser(
            @Field("email") email:String,
            @Field("password") password:String,
            @Field("username") username:String,
            @Field("name") name:String,
            @Field("lastname") lastname:String,
            @Field("phone") phone:String
    ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("auth")
    fun userLogin(
            @Field("email") email:String,
            @Field("password") password: String
    ):Call<LoginResponse>
}