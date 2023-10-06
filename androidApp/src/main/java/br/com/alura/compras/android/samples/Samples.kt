package br.com.alura.compras.android.samples

import br.com.alura.compras.android.models.Product
import java.time.LocalDateTime

val sampleProducts = List(5) { index ->
    Product(
        name = productsAsString,
        wasBought = index.mod(2) == 0,
        dateTime = LocalDateTime.now()
    )
}


private val productsAsString get() =  listOf(
    "Arroz",
    "Feijão",
    "Macarrão",
    "Leite",
    "Pão",
    "Ovos",
    "Azeite",
    "Sabão em Pó",
    "Shampoo",
    "Detergente",
    "Café",
    "Açúcar",
    "Sal",
    "Carne",
    "Frango",
    "Peixe",
    "Manteiga",
    "Queijo",
    "Presunto",
    "Molho de Tomate",
    "Batata",
    "Cenoura",
    "Cebola",
    "Alho",
    "Tomate",
    "Abacaxi",
    "Banana",
    "Maçã",
    "Uva",
    "Morango",
    "Laranja",
    "Limão",
    "Pêssego",
    "Abacate",
    "Pepino",
    "Brócolis",
    "Couve",
    "Alface",
    "Pimentão",
    "Milho",
    "Ervilha",
    "Feijão Verde",
    "Lentilha",
    "Quinoa",
    "Arroz Integral",
    "Macarrão Integral",
    "Iogurte",
    "Leite Condensado",
    "Creme de Leite"
).random()