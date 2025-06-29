package org.mytemplatewizard.project.repository

import kotlinx.coroutines.flow.Flow


interface SampleRepository {
    fun getIntFlow(): Flow<Int>
}
