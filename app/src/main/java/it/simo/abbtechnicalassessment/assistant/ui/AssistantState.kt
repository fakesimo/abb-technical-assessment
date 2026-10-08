package it.simo.abbtechnicalassessment.assistant.ui

data class ChatMessage(
    val text: String,
    val fromUser: Boolean,
)

data class AssistantState(
    val messages: List<ChatMessage> = emptyList(),
    val isThinking: Boolean = false,
) {
    fun toThinkingState(question: String) =
        copy(messages = messages + ChatMessage(question, fromUser = true), isThinking = true)

    fun toAnsweredState(answer: String) =
        copy(messages = messages + ChatMessage(answer, fromUser = false), isThinking = false)
}
