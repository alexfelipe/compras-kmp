package br.com.alura.compras.logger

enum class LogLevel {
    DEBUG, WARN, ERROR
}

internal expect fun logMessage(
    tag: String = "tag",
    message: String,
    level: LogLevel,
    throwable: Throwable? = null
)

fun logDebug(
    tag: String,
    message: String
) = logMessage(
    tag = tag,
    message = message,
    level = LogLevel.DEBUG
)

fun logWarn(
    tag: String,
    message: String
) = logMessage(
    tag = tag,
    message = message,
    level = LogLevel.WARN
)

fun logError(
    tag: String,
    message: String
) = logMessage(
    tag = tag,
    message = message,
    level = LogLevel.ERROR
)