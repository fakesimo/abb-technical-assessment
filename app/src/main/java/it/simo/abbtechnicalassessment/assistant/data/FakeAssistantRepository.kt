package it.simo.abbtechnicalassessment.assistant.data

import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class FakeAssistantRepository : AssistantRepository {
    override suspend fun send(text: String): String {
        delay(1500.milliseconds)
        return text
    }
}
