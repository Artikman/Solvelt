package com.example.listofspecialties.domain.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("f_name")
    var firstName: String?,
    @SerializedName("l_name")
    var lastName: String?,
    @SerializedName("birthday")
    var birthday: String?,
    @SerializedName("avatr_url")
    var avatarUrl: String?,
    @SerializedName("specialty")
    var speciality: List<Specialty>?
)