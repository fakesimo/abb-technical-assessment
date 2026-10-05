package it.simo.abbtechnicalassessment.repos.data

import it.simo.abbtechnicalassessment.repos.data.remote.GitHubApi
import it.simo.abbtechnicalassessment.repos.data.remote.toDomain
import it.simo.abbtechnicalassessment.repos.model.GitRepo

class RealGitRepoRepository(
    private val api: GitHubApi,
) : GitRepoRepository {
    override suspend fun getRepos(owner: String): List<GitRepo> =
        api.getRepositories(owner).map { it.toDomain() }

    override suspend fun getRepo(owner: String, name: String): GitRepo =
        api.getRepository(owner, name).toDomain()
}