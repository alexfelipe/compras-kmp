package br.com.alura.compras.logger

internal actual fun logMessage(
    tag: String,
    message: String,
    level: LogLevel,
    throwable: Throwable?
) {}