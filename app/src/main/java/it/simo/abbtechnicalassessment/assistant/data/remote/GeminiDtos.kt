package it.simo.abbtechnicalassessment.assistant.data.remote

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

// Request/response of POST /v1beta/interactions, see
// https://ai.google.dev/static/api/interactions.openapi.json
//
// Steps are kept as raw JsonObject on purpose: the model's steps (thought signatures
// included) must be sent back untouched, so we never map them to lossy classes.

@Serializable
data class InteractionRequest(
    val model: String,
    val input: List<JsonObject>,
    val tools: List<ToolDeclarationDto>,
    @SerialName("system_instruction") val systemInstruction: String? = null,
    @SerialName("previous_interaction_id") val previousInteractionId: String? = null,
    @SerialName("generation_config") val generationConfig: GenerationConfigDto? = null,
    @EncodeDefault val store: Boolean = false,
)

/** [thinkingLevel]: `minimal`, `low`, `medium` or `high`. Less thinking means faster answers. */
@Serializable
data class GenerationConfigDto(
    @SerialName("thinking_level") val thinkingLevel: String? = null,
)

@Serializable
data class ToolDeclarationDto(
    val name: String,
    val description: String,
    val parameters: JsonObject,
    @EncodeDefault val type: String = "function",
)

@Serializable
data class InteractionResponse(
    val id: String? = null,
    val status: String,
    val steps: List<JsonObject> = emptyList(),
)

object InteractionStatus {
    const val COMPLETED = "completed"
    const val REQUIRES_ACTION = "requires_action"
}

data class FunctionCall(
    val id: String,
    val name: String,
    val arguments: JsonObject,
)

private const val TYPE = "type"

private val JsonObject.stepType get() = this[TYPE]?.jsonPrimitive?.content

fun InteractionResponse.functionCalls(): List<FunctionCall> =
    steps.filter { it.stepType == "function_call" }
        .map {
            FunctionCall(
                id = it.getValue("id").jsonPrimitive.content,
                name = it.getValue("name").jsonPrimitive.content,
                arguments = it["arguments"]?.jsonObject ?: JsonObject(emptyMap()),
            )
        }

/** The model's text answer: every text item of every `model_output` step. */
fun InteractionResponse.outputText(): String =
    steps.asSequence()
        .filter { it.stepType == "model_output" }
        .flatMap { it["content"]?.jsonArray ?: JsonArray(emptyList()) }
        .map { it.jsonObject }
        .filter { it.stepType == "text" }
        .joinToString("") { it.getValue("text").jsonPrimitive.content }

fun userInput(text: String): JsonObject = buildJsonObject {
    put(TYPE, "user_input")
    put("content", buildJsonArray {
        add(buildJsonObject {
            put(TYPE, "text")
            put("text", text)
        })
    })
}
