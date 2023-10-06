package br.com.alura.compras.android.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDateTime.format(): String {
    val dateTimeFormatter = DateTimeFormatter
        .ofPattern(
            "EEEE (dd/MM/yyyy) 'às' HH:mm",
            Locale("pt", "BR")
        )
    return this.format(dateTimeFormatter)
}