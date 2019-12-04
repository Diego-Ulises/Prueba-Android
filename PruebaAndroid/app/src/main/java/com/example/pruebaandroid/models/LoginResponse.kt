package com.example.puebaandroid.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("token")
    val token: String,
    @field:SerializedName("user")
    val user: User
)