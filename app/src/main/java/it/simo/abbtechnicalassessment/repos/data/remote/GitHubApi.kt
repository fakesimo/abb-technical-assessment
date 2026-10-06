package it.simo.abbtechnicalassessment.repos.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GitHubApi(
    private val client: HttpClient,
) {
    private companion object {
        const val BASE_URL = "https://api.github.com"
        const val PAGE_SIZE = 50
    }

    suspend fun getRepositories(username: String): List<GitHubRepoDto> =
        client.get("$BASE_URL/users/$username/repos") {
            parameter("per_page", PAGE_SIZE)
        }.body()

    suspend fun getRepository(owner: String, repositoryName: String): GitHubRepoDto =
        client.get("$BASE_URL/repos/${owner}/$repositoryName").body()

}