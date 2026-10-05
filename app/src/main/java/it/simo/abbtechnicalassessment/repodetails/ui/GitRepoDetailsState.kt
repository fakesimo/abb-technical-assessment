package it.simo.abbtechnicalassessment.repodetails.ui

import it.simo.abbtechnicalassessment.repos.model.GitRepo

data class GitRepoDetailsState(
    val isLoading: Boolean = false,
    val gitRepo: GitRepo? = null,
    val error: String? = null,
) {
    fun toLoadingState() =
        copy(isLoading = true, gitRepo = null, error = null)

    fun toLoadedState(gitRepository: GitRepo) =
        copy(isLoading = false, gitRepo = gitRepository, error = null)

    fun toErrorState(error: String) =
        copy(isLoading = false, gitRepo = null, error = error)
}
