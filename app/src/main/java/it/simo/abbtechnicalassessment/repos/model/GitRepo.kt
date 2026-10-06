package it.simo.abbtechnicalassessment.repos.model

data class GitRepo(
    val owner: String,
    val name: String,
    val primaryLanguage: String,
    val starsNr: Int = 0,
)
