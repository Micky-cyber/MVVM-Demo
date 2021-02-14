package com.demomvvmapp.network

class ApiConstant {
    companion object {
        const val BASE_URL = ""
        const val SERVICE_URL = "?Service="
        const val GET_PROMOTED_WATCH_LIST = SERVICE_URL.plus("watch")

        const val SECRET_KEY: String = "secret_key"
        const val ACCESS_KEY: String = "access_key"
        const val DEVICE_TYPE: String = "device_type"
        const val DEVICE_TOKEN: String = "device_token"
        const val IS_TEST_DATA: String = "is_testdata"
        const val OFFSET: String = "offset"
    }

    enum class ResponseStatus(val status: String) {
        SUCCESS("1"),
        FAIL("0"),
    }
}
