package org.mytemplatewizard.project.repository

import co.touchlab.kermit.Logger


class LoggerRepositoryImpl() : LoggerRepository {
    override fun d(message: String) {
        Logger.d(message)
    }

    override fun e(message: String) {
        Logger.e(message)
    }
}