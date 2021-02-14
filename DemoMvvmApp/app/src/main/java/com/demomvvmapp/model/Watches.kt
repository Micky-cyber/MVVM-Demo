package com.demomvvmapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Watches(
    @SerializedName("watch_id")
    var watch_id: Int = 0,

    @SerializedName("brand_name")
    var brand_name: String? = "",

    @SerializedName("model_name")
    var model_name: String? = "",

    @SerializedName("seller_name")
    var seller_name: String? = "",

    @SerializedName("is_private_seller")
    var is_private_seller: Boolean = false,

    @SerializedName("is_pre_owned")
    var is_pre_owned: Boolean = false,

    @SerializedName("price")
    var price: Int = 0,

    @SerializedName("Media") val Media: ArrayList<Media> = ArrayList(),

    @SerializedName("is_deleted_watch")
    var is_deleted_watch: Boolean = false,

    @SerializedName("is_approved_watch")
    var is_approved_watch: Boolean = false,

    @SerializedName("is_top_promoted")
    var is_top_promoted: Boolean = false

) : Serializable

data class Media(
    @SerializedName("media_id")
    var media_id: Int = 0,

    @SerializedName("media_name")
    var media_name: String? = "",

    @SerializedName("media_type")
    var media_type: String? = ""
) : Serializable
