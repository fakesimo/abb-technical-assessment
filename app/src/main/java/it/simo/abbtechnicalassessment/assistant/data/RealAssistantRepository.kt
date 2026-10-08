package it.simo.abbtechnicalassessment.assistant.data

import android.util.Log
import it.simo.abbtechnicalassessment.assistant.data.remote.GeminiApi
import it.simo.abbtechnicalassessment.assistant.data.remote.GeminiApiMapper.toDto
import it.simo.abbtechnicalassessment.assistant.data.remote.GenerationConfigDto
import it.simo.abbtechnicalassessment.assistant.data.remote.InteractionRequest
import it.simo.abbtechnicalassessment.assistant.data.remote.functionCalls
import it.simo.abbtechnicalassessment.assistant.data.remote.outputText
import it.simo.abbtechnicalassessment.assistant.data.remote.userInput
import it.simo.abbtechnicalassessment.assistant.domain.AssistantTool

class RealAssistantRepository(
    private val api: GeminiApi,
    private val tool: AssistantTool,
) : AssistantRepository {
    private companion object {
        const val MODEL = "gemini-3.8-flash"
        val PROMPT = """
            You are an assistant that helps user understanding his/her github repositories using tools.
            Minimize token consumption
            """.trimIndent()
    }

    override suspend fun send(text: String): String {
        val response = api.createInteraction(
            InteractionRequest(
                model = MODEL,
                input = listOf(userInput(text)),
                tools = listOf(tool.toDto()),
                systemInstruction = PROMPT,
                generationConfig = GenerationConfigDto(thinkingLevel = "low"),
            )
        ).also {
            Log.d("ASSISTANT", it.toString())
        }

        val functionCall = response.functionCalls()
            .firstOrNull { it.name == "list_repos" }
            ?: return response.outputText()

        val toolResult = tool.execute(functionCall.arguments)
        return api.createInteraction(
            InteractionRequest(
                model = MODEL,
                previousInteractionId = response.id,
                input = listOf(userInput(toolResult)),
                tools = listOf(tool.toDto()),
                systemInstruction = PROMPT,
                generationConfig = GenerationConfigDto(thinkingLevel = "low"),
            )
        ).also {
            Log.d("ASSISTANT", it.toString())
        }.outputText()
    }
}
