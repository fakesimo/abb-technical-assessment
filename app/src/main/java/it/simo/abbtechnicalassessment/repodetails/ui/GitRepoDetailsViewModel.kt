package it.simo.abbtechnicalassessment.repodetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.simo.abbtechnicalassessment.repos.data.IGitRepoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GitRepoDetailsViewModel(
    private val name: String,
    private val repository: IGitRepoRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(GitRepoDetailsState(isLoading = true))
    val state = _state.asStateFlow()

    init {
        load()
    }

    fun onAction(action: GitRepoDetailsAction) {
        when (action) {
//            GitRepoDetailsAction.GoBack -> TODO()
            GitRepoDetailsAction.Load -> load()
        }
    }

    private fun load() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, gitRepo = null, error = null) }
            repository.getRepo(name)
                ?.let {
                    _state.update { old ->
                        old.copy(
                            isLoading = false,
                            gitRepo = it,
                            error = null,
                        )
                    }
                }
                ?: _state.update {
                    it.copy(
                        isLoading = false,
                        gitRepo = null,
                        error = "Repo $name not found! :O",
                    )
                }

        }
    }

}