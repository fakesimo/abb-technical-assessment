package it.simo.abbtechnicalassessment.repos.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubRepoDto(
    val name: String,
    val description: String? = null,
    @SerialName("stargazers_count") val stars: Int = 0,
    @SerialName("forks_count") val forks: Int = 0,
    @SerialName("owner") val ownerDto: OwnerDto,
)

@Serializable
data class OwnerDto(
    @SerialName("avatar_url") val avatarUrl: String,
    val login: String
)
