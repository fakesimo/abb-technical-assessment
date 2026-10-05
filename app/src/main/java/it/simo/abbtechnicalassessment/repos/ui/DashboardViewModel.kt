package it.simo.abbtechnicalassessment.repos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.simo.abbtechnicalassessment.repos.data.GitRepoRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val username: String,
    private val repository: GitRepoRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(DashboardState(isLoading = true))
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<DashboardEvent>()
    val events = _events.asSharedFlow()

    init {
        loadGitRepos()
    }

    fun onAction(action: DashboardAction) {
        when (action) {
            DashboardAction.Retry -> loadGitRepos()
            is DashboardAction.Click -> navigateToDetails(action)
        }
    }

    private fun loadGitRepos() {
        viewModelScope.launch {
            try {
                _state.update { it.toLoadingState() }
                val repos = repository.getRepos(username)
                _state.update { it.toLoadedState(repos) }
                _events.emit(DashboardEvent.ShowMessage("Loaded ${repos.size} repos"))
            } catch (ex: CancellationException) {
                throw ex
            } catch (ex: Exception) {
                _state.update { it.toErrorState("Couldn't load repos: ${ex.localizedMessage}") }
            }
        }
    }

    private fun navigateToDetails(action: DashboardAction.Click) {
        viewModelScope.launch {
            _events.emit(DashboardEvent.NavigateToDetails(action.owner, action.name))
        }
    }

}