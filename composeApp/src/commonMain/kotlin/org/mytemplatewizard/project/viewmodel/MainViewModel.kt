package org.mytemplatewizard.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.mytemplatewizard.project.repository.SampleRepository

class MainViewModel(
    private val sampleRepository: SampleRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        observeFlows()
    }

    private fun observeFlows() {
        sampleRepository.getIntFlow()
            .onEach { history ->
                _uiState.update {
                    it.copy(
                        sampleInt = history
                    )
                }
            }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }
}

data class MainUiState(
    val sampleInt: Int = 0,
)
