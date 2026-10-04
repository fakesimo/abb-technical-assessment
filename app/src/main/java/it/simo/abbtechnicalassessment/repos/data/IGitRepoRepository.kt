package it.simo.abbtechnicalassessment.repos.data

import it.simo.abbtechnicalassessment.repos.model.GitRepo

interface IGitRepoRepository {
    suspend fun getRepos(): List<GitRepo>
}