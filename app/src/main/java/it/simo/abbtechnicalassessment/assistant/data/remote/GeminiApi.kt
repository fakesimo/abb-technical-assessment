package it.simo.abbtechnicalassessment.assistant.data.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class GeminiApi(
    baseClient: HttpClient,
    private val apiKey: String,
) {
    private val client = baseClient.config {
        defaultRequest {
            url(BASE_URL)
            header("x-goog-api-key", apiKey)
        }
        install(HttpTimeout) {
            connectTimeoutMillis = 10_000
            socketTimeoutMillis = 60_000
            requestTimeoutMillis = 90_000
        }
    }

    private companion object {
        const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/"
    }

    suspend fun createInteraction(request: InteractionRequest): InteractionResponse {
        Log.d("GEMINI", Json.encodeToString(InteractionRequest.serializer(), request))
        return client.post("interactions") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}