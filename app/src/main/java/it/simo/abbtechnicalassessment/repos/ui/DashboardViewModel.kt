package it.simo.abbtechnicalassessment.repos.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.simo.abbtechnicalassessment.repos.model.GitRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class DashboardViewModel() : ViewModel() {
    private val _state = MutableStateFlow(DashboardState(isLoading = true))
    val state = _state.asStateFlow()

    init {
        loadGitRepos()
    }

    private fun dummyRepos() = generateSequence('a') { it + 1 }
        .map { GitRepo(name = it.toString(), language = "Kotlin") }
        .take(100)
        .toList()


    fun onAction(action: DashboardAction) {
        when (action) {
            DashboardAction.Retry -> loadGitRepos()
        }
    }

    private fun loadGitRepos() {
        Log.d("SIMO", "loadGitRepos")
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, gitRepositories = emptyList(), error = null) }
            delay(5.seconds)
            _state.update { it.copy(isLoading = false, gitRepositories = dummyRepos()) }
        }
    }

}