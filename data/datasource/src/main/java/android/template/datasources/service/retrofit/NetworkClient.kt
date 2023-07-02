package android.template.datasources.service.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class NetworkClient(baseUrl: String) {
    private val contentType: MediaType = "application/json".toMediaType()
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(buildOkHttpClient())
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()
}

private fun buildOkHttpClient(
    networkInterceptor: Interceptor? = null,
    localInterceptor: Interceptor? = null,
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        networkInterceptor?.let(::addNetworkInterceptor)
        localInterceptor?.let(::addInterceptor)
    }.build()
}
