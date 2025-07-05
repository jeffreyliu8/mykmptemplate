package org.mytemplatewizard.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mytemplatewizard.project.repository.LoggerRepository
import org.mytemplatewizard.project.repository.SampleKtorRepository
import org.mytemplatewizard.project.repository.SampleRepository

class MainViewModel(
    private val logger: LoggerRepository,
    private val sampleRepository: SampleRepository,
    private val sampleKtorRepository: SampleKtorRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        logger.d("MainViewModel init")
        observeFlows()
        testMakeHttpRequest()
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

    private fun testMakeHttpRequest() {
        viewModelScope.launch {
            val result = sampleKtorRepository.searchGithubRepos("square")
            if (result.isSuccess) {
                logger.d("HTTP request successful: ${result.getOrNull()?.totalCount}")
            } else {
                logger.e("HTTP request failed: ${result.exceptionOrNull()?.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        logger.d("MainViewModel onCleared")
    }
}

data class MainUiState(
    val sampleInt: Int = 0,
    val fakeList: ImmutableList<Int> = emptyList<Int>().toImmutableList(),
)
