package com.demomvvmapp.network

import com.demomvvmapp.model.Watches
import com.google.gson.annotations.SerializedName

abstract class ApiStatus {
    @SerializedName("status")
    var status: String = ""

    @SerializedName("message")
    var message: String = ""

    @SerializedName("data")
    var data: Data? = null

    inner class Data {
        @SerializedName("Watches")
        var watchList: ArrayList<Watches>? = null
    }
}