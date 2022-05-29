package com.demo.shaadi.data.model

import com.google.gson.annotations.SerializedName

data class ShaadiUsers(
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf(),
    @SerializedName("info") var info: Info? = Info()
)
