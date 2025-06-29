package org.mytemplatewizard.project.repository


interface LoggerRepository {
    fun d(message: String)
    fun e(message: String)
}
