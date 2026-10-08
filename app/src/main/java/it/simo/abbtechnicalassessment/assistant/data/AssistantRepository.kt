package it.simo.abbtechnicalassessment.assistant.data

interface AssistantRepository {
    suspend fun send(text: String): String
}