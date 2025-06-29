package org.mytemplatewizard.project.repository


interface LoggerRepository {
    fun v(message: String)
    fun d(message: String)
    fun e(message: String)
}
