package com.arun.androidThings.data

import com.google.gson.annotations.SerializedName

data class Details(val id:Int,
                   val email:String,
                   @SerializedName ("first_name") val firstName: String,
                   @SerializedName ("last_name") val lastName: String,
                   var isChecked:Boolean = false,
                   @SerializedName ("avatar") val avatar: String)