package it.simo.abbtechnicalassessment.repos.ui

import it.simo.abbtechnicalassessment.repos.model.GitRepo

data class DashboardState(
    val isLoading: Boolean = false,
    val gitRepositories: List<GitRepo> = emptyList(),
    val error: String? = null,
) {
    fun toLoadingState() =
        copy(isLoading = true, gitRepositories = emptyList(), error = null)

    fun toLoadedState(gitRepositories: List<GitRepo>) =
        copy(isLoading = false, gitRepositories = gitRepositories, error = null)

    fun toErrorState(error: String) =
        copy(isLoading = false, gitRepositories = emptyList(), error = error)
}
