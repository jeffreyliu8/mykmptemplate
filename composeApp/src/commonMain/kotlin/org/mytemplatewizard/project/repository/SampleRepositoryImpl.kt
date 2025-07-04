package org.mytemplatewizard.project.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class SampleRepositoryImpl() : SampleRepository {

    override fun getIntFlow() = flow {
        var i = 0
        while (true) {
            emit(i)
            i++
            delay(1000)
        }
    }.flowOn(Dispatchers.Default)
}