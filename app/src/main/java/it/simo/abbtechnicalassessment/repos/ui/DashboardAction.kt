package it.simo.abbtechnicalassessment.repos.ui

sealed interface DashboardAction {
    data object Retry : DashboardAction
    data class Click(val name: String): DashboardAction
}