package com.demomvvmapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.demomvvmapp.autoDispose
import com.demomvvmapp.model.Watches
import com.demomvvmapp.network.ApiInterface
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class HomeViewModel(private var appService: ApiInterface) : BaseViewModel() {

    var arrWatch: MutableLiveData<List<Watches>> = MutableLiveData()
    var status: MutableLiveData<String> = MutableLiveData()

    fun getWatch(
        secretKey: String,
        accessKey: String,
        deviceType: Int,
        deviceToken: String,
        isTestData: Int,
        offSet: Int
    ) {
        val params = JsonObject()
        params.addProperty("secret_key", secretKey)
        params.addProperty("access_key", accessKey)
        params.addProperty("device_type", deviceType)
        params.addProperty("device_token", deviceToken)
        params.addProperty("is_testdata", isTestData)
        params.addProperty("offeset", offSet)

        appService.getWatches(params)
            .doOnTerminate {
                isLoading.postValue(true)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isLoading.postValue(false)
                if (it.status == "1") {
                    arrWatch.value = it.data!!.watchList
                } else {
                    isFail.value = it.message
                }
            }, {
                isError.value = it
            }).autoDispose(compositeDisposable)
    }

    fun addWatchDetail(
        aa: String,
        bb: String,
        cc: String,
        dd: String,
        ee: String,
        watch_images: List<String>
    ) {
        val jobMediaData: ArrayList<MultipartBody.Part>? = ArrayList()
        if (watch_images.isNotEmpty()) {
            for (i in watch_images.indices) {
                val file = File(watch_images[i])  // Get File From Local File path
                val fileReqBody = RequestBody.create("image/*".toMediaType(), file)
                val profileImage =
                    MultipartBody.Part.createFormData("watch_images[$i]", file.name, fileReqBody)
                jobMediaData!!.add(profileImage)
            }
        }

        appService.addWatch(
            createStringRequestBody(aa)!!,
            createStringRequestBody(bb)!!,
            createStringRequestBody(cc)!!,
            createStringRequestBody(dd)!!,
            createStringRequestBody(ee)!!,
            jobMediaData
        )
            .doOnSubscribe {
                isLoading.postValue(true)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ loginRepo ->
                isLoading.postValue(false)
                if (loginRepo.status == "1") {
                    status.value = loginRepo.message
                } else {
                    isFail.value = loginRepo.message
                }
            }, { error ->
                isError.value = error
            }).autoDispose(compositeDisposable)
    }

    private fun createStringRequestBody(value: String?): RequestBody? {
        return if (value != null) {
            RequestBody.create("text/plain".toMediaTypeOrNull(), value)
        } else {
            null
        }
    }
}