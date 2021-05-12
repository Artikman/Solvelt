package com.example.listofspecialties.domain.model

import com.google.gson.annotations.SerializedName

data class WorkerResponse(
    @SerializedName("response")
    var response: List<Response>?
)