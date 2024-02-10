package com.example.composemovies.data.networking

import android.content.Context
import android.util.Log
import com.example.composemovies.R
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import javax.inject.Inject

class KtorClient @Inject constructor(
    private val context: Context,
) {

    fun getHttpClient() = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v(TAG_KTOR_LOGGER, message)
                }

            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d(TAG_HTTP_STATUS_LOGGER, "${response.status.value}")
            }
        }


        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            // https://www.themoviedb.org/settings/api
            // TODO pass Bearer Token
            // header(HttpHeaders.Authorization, "Bearer ey...")

            // https://www.linkedin.com/pulse/how-hide-protect-api-keys-your-android-app-oleksii-sylichenko
            // TODO getting TMDB api key, create a secure.properties file in the project root, store it there as API_KEY=MY_API_KEY
            parameter("api_key", context.getString(R.string.api_key))
        }
    }

    companion object {
        private const val TIME_OUT = 10_000
        private const val TAG_KTOR_LOGGER = "ktor_logger:"
        private const val TAG_HTTP_STATUS_LOGGER = "http_status:"
    }
}
