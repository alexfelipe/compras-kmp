package br.com.alura.compras.android.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.compras.android.MyApplicationTheme
import br.com.alura.compras.android.buttonColor
import br.com.alura.compras.android.googlefonts.montserratFontFamily
import br.com.alura.compras.android.samples.sampleProducts
import br.com.alura.compras.android.textButtonColor
import br.com.alura.compras.android.textColor
import br.com.alura.compras.android.ui.components.ProductItem
import br.com.alura.compras.android.ui.components.ProductsDivider
import br.com.alura.compras.android.ui.uistate.ProductListUiState

@Composable
fun ShoppingListScreen(
    uiState: ProductListUiState,
    modifier: Modifier = Modifier,
) {
    val productsToBuy = uiState.productsToBuy
    val boughtProducts = uiState.boughtProducts
    val lazyListState = rememberLazyListState()
    val productInputVisible by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex == 0 ||
                    !lazyListState.isScrollInProgress
        }
    }
    Column(
        modifier
            .fillMaxSize(),
    ) {
        AnimatedVisibility(visible = productInputVisible) {
            Column(Modifier.padding(16.dp)) {
                OutlinedTextField(value = uiState.productText,
                    onValueChange = uiState.onProductTextChange,
                    Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "Digite o item que deseja adicionar",
                            style = TextStyle(
                                fontFamily = montserratFontFamily,
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                color = textColor
                            )
                        )
                    })
                Spacer(modifier = Modifier.size(8.dp))
                Box(Modifier.fillMaxWidth()) {
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = buttonColor, shape = RoundedCornerShape(8.dp))
                            .padding(
                                vertical = 16.dp, horizontal = 8.dp
                            )
                            .clickable { uiState.onSaveProduct() }
                            .align(Alignment.Center),
                    ) {
                        Text(
                            text = "Salvar item",
                            color = textButtonColor,
                            style = TextStyle(
                                fontFamily = montserratFontFamily,
                                fontWeight = FontWeight(600),
                                fontSize = 20.sp
                            )
                        )
                    }
                }
            }
        }
        LazyColumn(
            Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = lazyListState,
            contentPadding = PaddingValues(16.dp)
        ) {
            if (productsToBuy.isNotEmpty()) {
                item {
                    ProductsDivider("Lista de compras")
                }
                items(productsToBuy) { product ->
                    ProductItem(
                        product,
                        onCheckedProductChange = uiState.onCheckedProductChange,
                        onDeleteProduct = uiState.onDeleteProduct,
                        onEditProduct = uiState.onEditProduct
                    )
                }
            }
            if (boughtProducts.isNotEmpty()) {
                item {
                    ProductsDivider("Comprados")
                }
                items(boughtProducts) { product ->
                    ProductItem(
                        product,
                        onCheckedProductChange = uiState.onCheckedProductChange,
                        onDeleteProduct = uiState.onDeleteProduct,
                        onEditProduct = uiState.onEditProduct
                    )
                }
            }
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
                )
            )
        }
    }
}