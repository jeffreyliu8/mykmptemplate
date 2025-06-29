package org.mytemplatewizard.project.repository

import co.touchlab.kermit.Logger


class LoggerRepositoryImpl() : LoggerRepository {
    private val tag = "kmpLog"
    override fun v(message: String) {
        Logger.v(tag) { message }
    }

    override fun d(message: String) {
        Logger.d(tag) { message }
    }

    override fun e(message: String) {
        Logger.e(tag) { message }
    }
}