package org.mytemplatewizard.project.viewmodel

import androidx.lifecycle.ViewModel
import org.mytemplatewizard.project.repository.LoggerRepository


class HomeViewModel(
    private val logger: LoggerRepository,
) : ViewModel() {
    init {
        logger.d("Home ViewModel initializing...")
    }

    override fun onCleared() {
        super.onCleared()
        logger.d("Home ViewModel clearing...")
    }
}