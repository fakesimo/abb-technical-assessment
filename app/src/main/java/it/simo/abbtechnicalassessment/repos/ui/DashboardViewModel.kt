package it.simo.abbtechnicalassessment.repos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.simo.abbtechnicalassessment.repos.model.GitRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class DashboardViewModel : ViewModel() {
    private val _state = MutableStateFlow(DashboardState(isLoading = true))
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<DashboardEvent>()
    val events = _events.asSharedFlow()

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
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, gitRepositories = emptyList(), error = null) }
            delay(5.seconds)
            val repos = dummyRepos()
            _state.update { it.copy(isLoading = false, gitRepositories = repos) }
            _events.emit(DashboardEvent.ShowMessage("Loaded ${repos.size} repos"))
//            _state.update { it.copy(isLoading = false, error = "BOOM!") }
        }
    }

}