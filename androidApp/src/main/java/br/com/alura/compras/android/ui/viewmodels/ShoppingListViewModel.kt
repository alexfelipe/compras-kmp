package br.com.alura.compras.android.ui.viewmodels

import androidx.lifecycle.ViewModel
import br.com.alura.compras.android.models.Product
import br.com.alura.compras.android.samples.sampleProducts
import br.com.alura.compras.android.ui.uistate.ProductListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime

class ShoppingListViewModel : ViewModel() {

    private val products = MutableStateFlow(sampleProducts)
    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState = _uiState
        .combine(products) { uiState, products ->
            uiState.copy(
                productsToBuy = products.filter { !it.wasBought },
                boughtProducts = products.filter { it.wasBought }
            )
        }

    init {
        _uiState.update {
            it.copy(
                onCheckedProductChange = { value, product ->
                    products.value = products.value.map { p ->
                        if (p.id == product.id) {
                            product.copy(wasBought = value)
                        } else {
                            p
                        }
                    }
                },
                onProductTextChange = { name ->
                    _uiState.value = _uiState.value.copy(productText = name)
                }
            )
        }
    }

    fun save() {
        val name = _uiState.value.productText
        if (products.value.find {
                it.name.uppercase() == name.uppercase()
            } == null) {
            products.update {
                listOf(
                    Product(
                        name = name,
                        dateTime = LocalDateTime.now()
                    )
                ) + it
            }
        }
        _uiState.update {
            it.copy(productText = "")
        }

    }

}