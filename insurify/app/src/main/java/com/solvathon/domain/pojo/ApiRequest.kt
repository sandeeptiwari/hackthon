package com.solvathon.domain.pojo

import com.google.gson.annotations.SerializedName

data class ApiRequest(
    @SerializedName("userId")
    var userId: Long? = null,
    @SerializedName("email")
    var email: String? = null
)
