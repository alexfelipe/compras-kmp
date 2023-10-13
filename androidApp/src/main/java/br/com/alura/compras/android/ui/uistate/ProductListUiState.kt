package br.com.alura.compras.android.ui.uistate

import br.com.alura.compras.android.models.Product

data class ProductListUiState(
    val productsToBuy: List<Product> = emptyList(),
    val boughtProducts: List<Product> = emptyList(),
    val onCheckedProductChange: (Product, Boolean) -> Unit = { _, _ -> },
    val productText: String = "",
    val onProductTextChange: (String) -> Unit = {},
    val onEditProduct: (Product, String) -> Unit = { _, _ -> },
    val onDeleteProduct: (Product) -> Unit = {},
    val onSaveProduct: () -> Unit = {},
)