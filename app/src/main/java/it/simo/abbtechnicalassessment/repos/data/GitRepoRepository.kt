package it.simo.abbtechnicalassessment.repos.data

import it.simo.abbtechnicalassessment.repos.model.GitRepo

interface GitRepoRepository {
    suspend fun getRepos(): List<GitRepo>
    suspend fun getRepo(owner: String, name: String): GitRepo
}