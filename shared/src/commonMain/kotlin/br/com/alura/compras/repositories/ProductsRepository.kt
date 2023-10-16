package br.com.alura.compras.repositories

import com.benasher44.uuid.uuid4
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class ProductsRepository {

    private val products = _products.asStateFlow()

    @NativeCoroutines
    val boughtProducts
        get() = products.flatMapLatest { entities ->
            flow {
                emit(entities.filter { it.wasBought })
            }
        }

    @NativeCoroutines
    val productsToBuy
        get() = products.flatMapLatest { entities ->
            flow {
                emit(entities.filter { !it.wasBought })
            }
        }

    fun toggleWasBought(id: String) {
        _products.update {
            _products.value.map { p ->
                if (id.uppercase() == p.id.uppercase()) {
                    p.copy(wasBought = !p.wasBought)
                } else {
                    p
                }
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

    fun delete(id: String) {
        val products = _products.value
        val index = products.indexOfFirst {
            it.id.uppercase() == id.uppercase()
        }
        if (index > -1) {
            _products.value = products.filterNot {
                it.id.uppercase() == id.uppercase()
            }
        }
    }

    fun edit(id: String, name: String) {
        _products.value = _products.value.map { p ->
            if (id.uppercase() == p.id.uppercase()) {
                p.copy(name = name)
            } else {
                p
            }
        }
    }

    companion object {
        private val _products =
            MutableStateFlow<List<ProductEntity>>(emptyList())
    }

}

data class ProductEntity(
    val id: String,
    val name: String,
    val wasBought: Boolean = false,
    val dateTime: Long = Clock.System.now().toEpochMilliseconds(),
)