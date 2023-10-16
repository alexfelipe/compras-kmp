package br.com.alura.compras.repositories

import com.benasher44.uuid.uuid4
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
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
    val boughtProducts get() =  products.flatMapLatest { entities ->
        flow {
            emit(entities.filter { it.wasBought })
        }
    }
    @NativeCoroutines
    val productsToBuy get() = products.flatMapLatest { entities ->
        flow {
            emit(entities.filter { !it.wasBought })
        }
    }

    @NativeCoroutines
    val alex = flow<String> {
        emit("alex")
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

    fun delete(id: String) {
        val products = _products.value
        val index = products.indexOfFirst {
            it.id == id
        }

        if(index > -1) {
            _products.value = products.filterNot { it.id == id }
        }
    }

    fun edit(id: String, name: String) {
        _products.value = _products.value.map { p ->
            if(id == p.id) {
                p.copy(name = name)
            } else {
                p
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