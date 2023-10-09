package br.com.alura.compras.android.ui.uistate

import br.com.alura.compras.android.models.Product

data class ProductListUiState(
    val productsToBuy: List<Product> = emptyList(),
    val boughtProducts: List<Product> = emptyList(),
    val onCheckedProductChange: (Boolean, Product) -> Unit = { _, _ -> },
    val productText: String = "",
    val onProductTextChange: (String) -> Unit = {}
)