package it.simo.abbtechnicalassessment.repodetails.ui

import it.simo.abbtechnicalassessment.repos.model.GitRepo

data class GitRepoDetailsState(
    val isLoading: Boolean = false,
    val gitRepo: GitRepo? = null,
    val error: String? = null,
)
