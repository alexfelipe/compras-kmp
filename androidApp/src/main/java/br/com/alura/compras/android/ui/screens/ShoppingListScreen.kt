package br.com.alura.compras.android.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.compras.android.MyApplicationTheme
import br.com.alura.compras.android.models.Product
import br.com.alura.compras.android.samples.sampleProducts
import br.com.alura.compras.android.ui.uistate.ProductListUiState

@Composable
fun ShoppingListScreen(
    uiState: ProductListUiState,
    modifier: Modifier = Modifier,
) {
    val productsToBuy = uiState.productsToBuy
    val boughtProducts = uiState.boughtProducts
    Column(modifier) {
        var product by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = product,
            onValueChange = {
                product = it
            },
            Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Digite o item que deseja adicionar") }
        )
        Box(Modifier.fillMaxWidth()) {
            Button(onClick = { /*TODO*/ }, Modifier.align(Alignment.Center)) {
                Text(text = "Salvar item")
            }
        }
        LazyColumn {
            if (productsToBuy.isNotEmpty()) {
                item {
                    Text(text = "Lista de compras")
                    Canvas(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .height(1.dp)
                    ) {
                        drawLine(
                            color = Color.Blue,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 20f), 0f),
                            strokeWidth = 10f,
                        )
                    }
                }
                items(productsToBuy) { product ->
                    ProductItem(product, uiState.onCheckedProductChange)
                }
            }
            if (boughtProducts.isNotEmpty()) {
                item {
                    Text(text = "Comprados")
                    Canvas(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .height(1.dp)
                    ) {
                        drawLine(
                            color = Color.Blue,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 20f), 0f),
                            strokeWidth = 10f,
                        )
                    }
                }
                items(boughtProducts) { product ->
                    ProductItem(product, uiState.onCheckedProductChange)
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ProductItem(
    product: Product,
    onCheckedProductChange: (Boolean, Product) -> Unit
) {
    Row(Modifier.padding(16.dp)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    Modifier
                        .weight(1f)
                ) {
                    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                        Checkbox(
                            checked = product.wasBought,
                            onCheckedChange = {
                                onCheckedProductChange(it, product)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(text = product.name)
                }
                Row(verticalAlignment = Alignment.Top) {
                    Icon(Icons.Filled.Delete, contentDescription = "delete icon")
                    Spacer(modifier = Modifier.size(8.dp))
                    Icon(Icons.Filled.Edit, contentDescription = "edit icon")
                }
            }
            Text(text = product.dateTime.toString())
        }

    }
}

@Preview
@Composable
fun ShoppingListPreview() {
    MyApplicationTheme {
        Surface(color = Color.White) {
            ShoppingListScreen(
                uiState = ProductListUiState(
                    boughtProducts = sampleProducts.filter { it.wasBought },
                    productsToBuy = sampleProducts.filter { !it.wasBought },
                ),
            )
        }
    }
}