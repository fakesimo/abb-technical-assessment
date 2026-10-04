package it.simo.abbtechnicalassessment.repos.ui

import it.simo.abbtechnicalassessment.repos.model.GitRepo

data class DashboardState(
    val isLoading: Boolean = false,
    val gitRepositories: List<GitRepo> = emptyList(),
    val error: String? = null,
)

sealed interface DashboardAction{
    data object Retry: DashboardAction
}