package br.com.alura.compras.android.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.compras.android.MyApplicationTheme
import br.com.alura.compras.android.buttonColor
import br.com.alura.compras.android.checkmarkColor
import br.com.alura.compras.android.extensions.format
import br.com.alura.compras.android.googlefonts.mansalvaFontFamily
import br.com.alura.compras.android.googlefonts.montserratFontFamily
import br.com.alura.compras.android.iconColor
import br.com.alura.compras.android.mainColor
import br.com.alura.compras.android.models.Product
import br.com.alura.compras.android.samples.sampleProducts
import br.com.alura.compras.android.textButtonColor
import br.com.alura.compras.android.textColor
import br.com.alura.compras.android.ui.uistate.ProductListUiState
import br.com.alura.compras.android.uncheckedColor

@Composable
fun ShoppingListScreen(
    uiState: ProductListUiState,
    modifier: Modifier = Modifier,
    onSaveProduct: () -> Unit,
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
                            .clickable { onSaveProduct() }
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
                    ProductItem(product, uiState.onCheckedProductChange)
                }
            }
            if (boughtProducts.isNotEmpty()) {
                item {
                    ProductsDivider("Comprados")
                }
                items(boughtProducts) { product ->
                    ProductItem(product, uiState.onCheckedProductChange)
                }
            }
        }
    }
}

@Composable
private fun ProductsDivider(title: String) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = title,
            fontSize = 32.sp,
            fontFamily = mansalvaFontFamily,
            fontWeight = FontWeight(400),
            color = mainColor
        )
        Canvas(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)

                .height(1.dp)
        ) {
            drawLine(
                color = mainColor,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 20f), 0f),
                strokeWidth = 10f,
            )
        }
    }
}

@Composable
private fun ProductItem(
    product: Product,
    onCheckedProductChange: (Boolean, Product) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        val textDecoration = if (product.wasBought) {
            TextDecoration.LineThrough
        } else {
            TextDecoration.None
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .size(20.dp)
                            .border(BorderStroke(2.dp, color = uncheckedColor))
                            .background(Color.Transparent)
                            .clickable {
                                onCheckedProductChange(
                                    !product.wasBought,
                                    product
                                )
                            }
                    ) {
                        if (product.wasBought) {
                            Icon(
                                Icons.Filled.Done,
                                contentDescription = "checked icon",
                                tint = checkmarkColor
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = product.name, style = TextStyle(
                            fontFamily = montserratFontFamily,
                            color = textColor,
                            fontSize = 18.sp
                        ),
                        fontWeight = FontWeight(600)
                    )
                }
                Row(verticalAlignment = Alignment.Top) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "delete icon",
                        tint = iconColor
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "edit icon",
                        tint = iconColor
                    )
                }
            }
            Text(
                text = product.dateTime.format(), style = TextStyle(
                    textDecoration = textDecoration,
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight(400),
                    color = textColor
                )
            )
        }

    }
}

@Preview
@Composable
fun ShoppingListPreview() {
    MyApplicationTheme {
        Surface(color = Color.White) {
            ShoppingListScreen(uiState = ProductListUiState(
                boughtProducts = sampleProducts.filter { it.wasBought },
                productsToBuy = sampleProducts.filter { !it.wasBought },
            ), onSaveProduct = {})
        }
    }
}