package it.simo.abbtechnicalassessment.repos.model

data class GitRepo(
    val name: String,
    val description: String = "",
    val language: String,
    val starsNr: Int = 0,
    val forksNr: Int = 0,
)
