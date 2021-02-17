package com.demomvvmapp.network

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    private var retrofit: Retrofit? = null

    fun createService(context: Context) : ApiInterface{
        return setOkHttp(context).create(ApiInterface::class.java)
    }

    fun setOkHttp(context: Context) : Retrofit{

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .client(client)
            .build()

        return retrofit!!
    }
    
    
    fun createService(context: Context): ApiService {
        return setupRetrofit(context).create(ApiService::class.java)
//     }

//     lateinit var prefManager: PrefManager

//     fun setupOkHttp(context: Context): OkHttpClient {
//         prefManager = PrefManager(context)
//         val cacheSize = 10 * 1024 * 1024 // 10 MiB
//         val cacheDir = File(context.cacheDir, "HttpCache")
//         val cache = Cache(cacheDir, cacheSize.toLong())

//         //TODO Replace sample_certificate.pem with your server public certificate in raw resource and uncomment .setupNetworkSecurity(context)
//         val builder = OkHttpClient.Builder()
//             .readTimeout(20, TimeUnit.SECONDS)
//             .connectTimeout(20, TimeUnit.SECONDS)
//             .setupNetworkSecurity(context)
//             .cache(cache)

//         if (BuildConfig.DEBUG) {
//             val loggingInterceptor = HttpLoggingInterceptor()
//             loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//             builder.addInterceptor(loggingInterceptor)
//             builder.addInterceptor { chain ->
//                 val newRequest = chain.request().newBuilder()
//                     .addHeader(AppConstants.ACCESS_KEY, prefManager.getAccessKey())
//                     .addHeader(AppConstants.SECRET_KEY, prefManager.getSecretKey())
//                     .addHeader(AppConstants.DEVICE_TYPE, "2")
//                     .addHeader(AppConstants.USER_AGENT, "Android")
//                     .addHeader(AppConstants.DEVICE_TOKEN, prefManager.getDeviceToken())
//                     .build()

//                 chain.proceed(newRequest)
//             }
//         }
//         return builder.build()
//     }

//     private fun setupRetrofit(context: Context): Retrofit {
//         return Retrofit.Builder()
//             .baseUrl(ApiConstants.ApiURL.BASE_URL)
//             .client(setupOkHttp(context))
//             .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync()) // Using create async means all api calls are automatically created asynchronously using OkHttp's thread pool
//             .addConverterFactory(
//                 GsonConverterFactory.create(
//                     GsonBuilder().registerTypeAdapter(
//                         Date::class.java,
//                         JsonDeserializer { json, _, _ -> Date(json.asJsonPrimitive.asLong) })
//                         .create()
//                 )
//             )
//             .build()
//     }
    
}
