package it.simo.abbtechnicalassessment.repos.ui

import androidx.lifecycle.ViewModel
import it.simo.abbtechnicalassessment.repos.model.GitRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel() : ViewModel() {
    private val _state = MutableStateFlow(DashboardState(gitRepositories = dummyRepos()))
    val state = _state.asStateFlow()

    private fun dummyRepos() = generateSequence('a') { it + 1 }
        .map { GitRepo(name = it.toString(), language = "Kotlin") }
        .take(100)
        .toList()

}