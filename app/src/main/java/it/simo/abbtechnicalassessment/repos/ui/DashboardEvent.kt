package it.simo.abbtechnicalassessment.repos.ui

sealed interface DashboardEvent {
    data class ShowMessage(val text: String) : DashboardEvent
    data class NavigateToDetails(val name: String): DashboardEvent
}