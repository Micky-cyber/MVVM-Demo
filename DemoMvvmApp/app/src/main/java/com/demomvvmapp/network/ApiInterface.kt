package com.demomvvmapp.network

import com.demomvvmapp.repository.BaseRepository
import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiInterface {
    @POST(ApiConstant.GET_PROMOTED_WATCH_LIST)
    fun getWatches(@Body params: JsonObject): Observable<BaseRepository>

    @Multipart
    @POST("add")
    fun addWatch(
        @Part("secret_key") secret_key: RequestBody,
        @Part("access_key") access_key: RequestBody,
        @Part("device_type") device_type: RequestBody,
        @Part("device_token") device_token: RequestBody,
        @Part("is_testdata") is_testdata: RequestBody,
        @Part watch_images: List<MultipartBody.Part>? = null
    ): Observable<BaseRepository>
}