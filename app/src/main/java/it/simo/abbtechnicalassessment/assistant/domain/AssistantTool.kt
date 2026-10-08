package it.simo.abbtechnicalassessment.assistant.domain

import it.simo.abbtechnicalassessment.repos.data.GitRepoRepository
import it.simo.abbtechnicalassessment.repos.model.GitRepo
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

interface AssistantTool {
    val name: String
    val description: String
    val parameters: JsonObject
    suspend fun execute(arguments: JsonObject): String
}

class ListReposTool(
    private val gitRepoRepository: GitRepoRepository,
) : AssistantTool {
    override val name: String =
        "list_repos"
    override val description: String =
        "Lists the user's GitHub repositories with their language and stars."
    override val parameters: JsonObject =
        buildJsonObject {
            put("type", "object")
            put("properties", buildJsonObject { })
        }


    override suspend fun execute(arguments: JsonObject): String {
        val repos = gitRepoRepository.getRepos()
        return if (repos.isEmpty()) "No repositories" else repos.joinToString(separator = "\n") { it.toToolString() }
    }

    private fun GitRepo.toToolString() = "$name | $primaryLanguage | $starsNr stars"
}