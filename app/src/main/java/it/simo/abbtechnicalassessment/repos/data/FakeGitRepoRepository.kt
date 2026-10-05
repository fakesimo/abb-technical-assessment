package it.simo.abbtechnicalassessment.repos.data

import it.simo.abbtechnicalassessment.repos.model.GitRepo
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class FakeGitRepoRepository : GitRepoRepository {
    override suspend fun getRepos(): List<GitRepo> {
        delay(2.seconds)
        return generateSequence('a') { it + 1 }
            .map { GitRepo(name = it.toString()) }
            .take(40)
            .toList()
    }

    override suspend fun getRepo(name: String): GitRepo? {
        delay(500.milliseconds)
        return if (name == "z") {
            null
        } else {
            GitRepo(name = name, starsNr = Random.nextInt(0, 10_000))
        }
    }
}