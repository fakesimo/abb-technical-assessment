package it.simo.abbtechnicalassessment.assistant.data.remote

import it.simo.abbtechnicalassessment.assistant.domain.AssistantTool

object GeminiApiMapper {

    fun AssistantTool.toDto(): ToolDeclarationDto =
        ToolDeclarationDto(
            name = name,
            description = description,
            parameters = parameters,
        )

}