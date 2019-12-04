package com.example.puebaandroid.models

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("id")
    val id:String?,
    @field:SerializedName("email")
    val email:String?,
    @field:SerializedName("username")
    val username:String?,
    @field:SerializedName("name")
    val name:String?,
    @field:SerializedName("lastname")
    val lastname:String?,
    @field:SerializedName("phone")
    val phone:String?
)