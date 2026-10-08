package it.simo.abbtechnicalassessment.assistant.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.simo.abbtechnicalassessment.assistant.data.AssistantRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssistantViewModel(
    private val repository: AssistantRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(AssistantState())
    val state = _state.asStateFlow()

    fun onAction(action: AssistantAction) {
        when (action) {
            is AssistantAction.Send -> send(action.text)
        }
    }

    private fun send(text: String) {
        val question = text.trim()
        if (question.isEmpty() || _state.value.isThinking) return

        viewModelScope.launch {
            _state.update { it.toThinkingState(question) }
            try {
                val answer = repository.send(question)
                _state.update { it.toAnsweredState(answer) }
            } catch (ex: CancellationException) {
                throw ex
            } catch (ex: Exception) {
                Log.e("ASSISTANT", "send failed", ex)
                _state.update { it.toAnsweredState("Sorry, something went wrong. Please try again.") }
            }
        }
    }
}
