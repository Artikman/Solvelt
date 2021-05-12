package com.example.listofspecialties.domain.model

import com.google.gson.annotations.SerializedName

data class Specialty(
    @SerializedName("specialty_id")
    var specialityId: Int?,
    @SerializedName("name")
    var specialityName: String?
)