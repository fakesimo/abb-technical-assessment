package it.simo.abbtechnicalassessment.repos.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class GitHubApi(
    private val client: HttpClient,
) {
    suspend fun getRepositories(username: String): List<GitHubRepoDto> =
        client.get("users/$username/repos").body()
}