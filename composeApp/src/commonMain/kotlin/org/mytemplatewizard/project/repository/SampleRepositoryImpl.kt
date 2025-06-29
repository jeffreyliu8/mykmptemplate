package org.mytemplatewizard.project.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow


class SampleRepositoryImpl() : SampleRepository {

    override fun getIntFlow() = flow {
        var i = 0
        while (true) {
            emit(i)
            i++
            delay(1000)
        }
    }
}