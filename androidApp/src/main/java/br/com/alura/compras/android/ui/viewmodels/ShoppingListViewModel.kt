package br.com.alura.compras.android.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.compras.android.models.Product
import br.com.alura.compras.android.ui.uistate.ProductListUiState
import br.com.alura.compras.repositories.ProductEntity
import br.com.alura.compras.repositories.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone

class ShoppingListViewModel : ViewModel() {

    private val repository: ProductsRepository = ProductsRepository()
    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                onCheckedProductChange = { value, product ->
                    repository.productWasBought(product.id, value)
                },
                onProductTextChange = { name ->
                    _uiState.value = _uiState.value.copy(productText = name)
                }
            )
        }
        viewModelScope.launch {
            val productsToBuyFlow = repository
                .productsToBuy.mapLatest { entities ->
                    entities.map { it.toProduct() }
                }
            val boughtProductsFlow = repository
                .boughtProducts.mapLatest { entities ->
                    entities.map { it.toProduct() }
                }
            combine(
                productsToBuyFlow,
                boughtProductsFlow,
                ::ProductListUiState
            ).collectLatest { newState ->
                _uiState.update {
                    it.copy(
                        boughtProducts = newState.boughtProducts,
                        productsToBuy = newState.productsToBuy
                    )
                }
            }
        }
    }

    fun save() {
        val name = _uiState.value.productText
        _uiState.update {
            it.copy(productText = "")
        }
        repository.save(name)
    }

}

fun ProductEntity.toProduct() = Product(
    id = this.id,
    name = this.name,
    wasBought = this.wasBought,
    dateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(this.dateTime),
        TimeZone.getDefault().toZoneId()
    )
)