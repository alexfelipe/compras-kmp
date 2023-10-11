package br.com.alura.compras.repositories

import com.benasher44.uuid.uuid4
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock

class ProductsRepository {

    private val products = _products.asStateFlow()
    val boughtProducts = products.flatMapLatest { entities ->
        flow {
            emit(entities.filter { it.wasBought })
        }
    }
    val productsToBuy = products.flatMapLatest { entities ->
        flow {
            emit(entities.filter { !it.wasBought })
        }
    }

    fun productWasBought(id: String, value: Boolean) {
        _products.value = _products.value.map { p ->
            if (id == p.id) {
                p.copy(wasBought = value)
            } else {
                p
            }
        }
    }

    fun save(name: String) {
        if (products.value.find {
                it.name.uppercase() == name.uppercase()
            } == null) {
            _products.update {
                listOf(
                    ProductEntity(
                        id = uuid4().toString(),
                        name = name
                    )
                ) + it
            }
        }
    }

    companion object {
        private val _products: MutableStateFlow<List<ProductEntity>> = MutableStateFlow(emptyList())
    }

}

data class ProductEntity(
    val id: String,
    val name: String,
    val wasBought: Boolean = false,
    val dateTime: Long = Clock.System.now().toEpochMilliseconds(),
)