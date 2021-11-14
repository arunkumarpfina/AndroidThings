package com.arun.androidThings.data

import com.google.gson.annotations.SerializedName

data class Things(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    @SerializedName ("data") val thingsDetails: List<Details>
    )