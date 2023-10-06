package br.com.alura.compras.android.models

import kotlinx.datetime.LocalDateTime
import java.util.UUID

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val wasBought: Boolean = false,
    val dateTime: LocalDateTime,
)
