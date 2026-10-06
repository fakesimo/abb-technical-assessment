package it.simo.abbtechnicalassessment.repos.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders

class GitHubApi(
    baseClient: HttpClient,
    private val token: String,
) {
    private val client = baseClient.config {
        defaultRequest {
            url(BASE_URL)
            header(HttpHeaders.Authorization, "Bearer $token")
            header(HttpHeaders.Accept, "application/vnd.github+json")
        }
    }

    private companion object {
        const val BASE_URL = "https://api.github.com"
        const val PAGE_SIZE = 50
    }

    suspend fun getRepositories(): List<GitHubRepoDto> =
        client.get("user/repos") {
            parameter("per_page", PAGE_SIZE)
        }.body()

    suspend fun getRepository(owner: String, name: String): GitHubRepoDto =
        client.get("repos/${owner}/$name").body()

}