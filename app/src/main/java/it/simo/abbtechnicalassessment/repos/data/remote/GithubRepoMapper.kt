package it.simo.abbtechnicalassessment.repos.data.remote

import it.simo.abbtechnicalassessment.repos.model.GitRepo

internal fun GitHubRepoDto.toDomain(): GitRepo =
    GitRepo(
        owner = ownerDto.login,
        name = name,
        primaryLanguage = language.orEmpty(),
        starsNr = stars,
    )