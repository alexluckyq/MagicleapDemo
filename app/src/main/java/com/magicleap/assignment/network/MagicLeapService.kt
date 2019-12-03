package com.magicleap.assignment.network

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.magicleap.assignment.model.Coffee
import com.magicleap.assignment.model.CoffeeList
import com.magicleap.assignment.utils.NetworkUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object MagicLeapService {

    private val HOST = "http://demo1076779.mockable.io/"
    private val TAG = "MagicLeap Service"

    private var magicLeapApi: MagicLeapApi? = null

    private fun initApi(context: Context) {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (NetworkUtils.hasNetwork(context)!!) {
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                } else {
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                }
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        magicLeapApi = retrofit.create<MagicLeapApi>(MagicLeapApi::class.java!!)
    }

    fun retrieveCoffeeList(context: Context, callback: OnRequestCallback<CoffeeList>) {
        if (magicLeapApi == null) {
            initApi(context)
        }

        Thread(Runnable {
            try {
                val response = magicLeapApi?.getCoffeeList()?.execute()
                Handler(Looper.getMainLooper()).post {
                    if (response?.isSuccessful()?:false) {
                        callback.onSuccess(response?.body()?:null)

                    } else {
                        callback.onFailure("Failed call to getCoffeeList")
                    }
                }
            } catch (e: IOException) {
                Log.e(TAG, "getCoffeeeList failed", e)
                callback.onFailure(e.message?:"")
            }
        }).start()
    }

    fun retrieveCoffeeDetail(context: Context, id: String, callback: OnRequestCallback<Coffee>) {
        if (magicLeapApi == null) {
            initApi(context)
        }

        Thread(Runnable {
            try {
                val response = magicLeapApi?.getCoffeeDetail(id)?.execute()
                Handler(Looper.getMainLooper()).post {
                    if (response?.isSuccessful()?:false) {
                        callback.onSuccess(response?.body()?:null)
                    } else {
                        callback.onFailure("Failed call to getCoffeeDetail")
                    }
                }
            } catch (e: IOException) {
                Log.e(TAG, "getCoffeeeList failed", e)
                callback.onFailure(e.message?:"")
            }
        }).start()
    }


}