package com.example.thejokerer.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

/**
 * Interceptor class for handling network connectivity checks in Retrofit requests.
 *
 * The `NetworkConnectionInterceptor` class implements the [Interceptor] interface to intercept
 * Retrofit requests and check for network connectivity before making the request. It throws
 * an [IOException] if there is no active network connection.
 *
 * @property context The [Context] used to access connectivity services.
 */
class NetworkConnectionInterceptor(private val context: Context) : Interceptor {


    /**
     * Intercepts the Retrofit request chain to check for network connectivity.
     *
     * @param chain The [Interceptor.Chain] representing the request chain.
     * @return The [Response] object if the network is available, or an exception if not.
     * @throws IOException If there is no active network connection.
     */
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        if (!isConnected(context=context)) {
            Log.i("retrofit", "there is no connection")
            throw IOException()
        } else {
            val builder = chain.request().newBuilder()
            return@run chain.proceed(builder.build())
        }
    }

    /**
     * Checks if there is an active network connection.
     *
     * @param context The [Context] used to access connectivity services.
     * @return `true` if there is an active network connection, `false` otherwise.
     */
    private fun isConnected(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        
        return result
    }
}
