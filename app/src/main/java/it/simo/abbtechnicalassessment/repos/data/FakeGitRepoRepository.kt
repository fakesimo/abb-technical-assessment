package it.simo.abbtechnicalassessment.repos.data

import it.simo.abbtechnicalassessment.repos.model.GitRepo
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class FakeGitRepoRepository : IGitRepoRepository {
    override suspend fun getRepos(): List<GitRepo> {
        delay(2.seconds)
        return generateSequence('a') { it + 1 }
            .map { GitRepo(name = it.toString(), language = "Kotlin") }
            .take(100)
            .toList()
    }
}