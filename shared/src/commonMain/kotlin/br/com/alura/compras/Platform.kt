package br.com.alura.compras

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform