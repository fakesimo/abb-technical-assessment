package it.simo.abbtechnicalassessment.repos.data.remote

import it.simo.abbtechnicalassessment.repos.model.GitRepo

internal fun GitHubRepoDto.toDomain(): GitRepo =
    GitRepo(
        name = name,
        description = description.orEmpty(),
        starsNr = stars,
        forksNr = forks,
    )