package br.com.alura.compras.logger

import android.util.Log

internal actual fun logMessage(
    tag: String,
    message: String,
    level: LogLevel,
    throwable: Throwable?
) {
    when(level) {
        LogLevel.DEBUG -> Log.d(tag, message)
        LogLevel.WARN -> Log.w(tag, message)
        LogLevel.ERROR -> Log.e(tag, message, throwable)
    }
}