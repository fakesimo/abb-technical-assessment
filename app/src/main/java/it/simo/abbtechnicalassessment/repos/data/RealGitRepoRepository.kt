package it.simo.abbtechnicalassessment.repos.data

import it.simo.abbtechnicalassessment.repos.data.remote.GitHubApi
import it.simo.abbtechnicalassessment.repos.data.remote.toDomain
import it.simo.abbtechnicalassessment.repos.model.GitRepo

class RealGitRepoRepository(
    private val api: GitHubApi,
) : GitRepoRepository {
    override suspend fun getRepos(): List<GitRepo> =
        api.getRepositories("JetBrains").map { it.toDomain() }

    override suspend fun getRepo(name: String): GitRepo? =
        getRepos().firstOrNull { it.name == name }
}