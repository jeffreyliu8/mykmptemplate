package org.mytemplatewizard.project.repository

import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


private const val KEY = "Key"

class SampleRepositoryImpl(
    private val settings: Settings
) : SampleRepository {

    override fun getIntFlow() = flow {
        var i = 0
        while (true) {
            emit(i)
            i++
            delay(1000)
        }
    }.flowOn(Dispatchers.Default)

    override fun setSetting(value: String) {
        settings.putString(KEY, value)
    }

    override fun getSettings(): String {
        return settings.getString(KEY, "")
    }
}