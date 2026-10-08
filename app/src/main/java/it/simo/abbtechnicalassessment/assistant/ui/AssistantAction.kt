package it.simo.abbtechnicalassessment.assistant.ui

sealed interface AssistantAction {
    data class Send(val text: String) : AssistantAction
}
