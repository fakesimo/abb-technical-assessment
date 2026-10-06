package it.simo.abbtechnicalassessment.repos.ui

sealed interface DashboardAction {
    data object Retry : DashboardAction
    data class Click(val owner: String, val name: String): DashboardAction
}