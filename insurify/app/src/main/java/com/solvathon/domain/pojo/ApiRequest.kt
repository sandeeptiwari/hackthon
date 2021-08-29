package com.solvathon.domain.pojo

import com.google.gson.annotations.SerializedName

data class ApiRequest(
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("password")
    var password: String? = null
)
