package org.mytemplatewizard.project.viewmodel

import androidx.lifecycle.ViewModel
import org.mytemplatewizard.project.repository.LoggerRepository

class DetailViewModel(
    private val logger: LoggerRepository,
) : ViewModel() {
    init {
        logger.d("Detail ViewModel initializing...")
    }

    override fun onCleared() {
        super.onCleared()
        logger.d("Detail ViewModel clearing...")
    }
}