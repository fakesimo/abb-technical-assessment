package it.simo.abbtechnicalassessment.repodetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.simo.abbtechnicalassessment.repos.data.GitRepoRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GitRepoDetailsViewModel(
    private val owner: String,
    private val name: String,
    private val repository: GitRepoRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(GitRepoDetailsState(isLoading = true))
    val state = _state.asStateFlow()

    init {
        load()
    }

    fun onAction(action: GitRepoDetailsAction) {
        when (action) {
            GitRepoDetailsAction.Load -> load()
        }
    }

    private fun load() {
        viewModelScope.launch {
            _state.update { it.toLoadingState() }
            try {
                val repo = repository.getRepo(owner, name)
                _state.update { old -> old.toLoadedState(repo) }
            } catch (ex: CancellationException) {
                throw ex
            } catch (ex: Exception) {
                _state.update { it.toErrorState("Couldn't load repo: ${ex.localizedMessage}") }
            }
        }
    }

}