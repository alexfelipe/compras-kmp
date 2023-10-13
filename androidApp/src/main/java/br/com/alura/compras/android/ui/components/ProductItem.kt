package br.com.alura.compras.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.compras.android.MyApplicationTheme
import br.com.alura.compras.android.checkmarkColor
import br.com.alura.compras.android.extensions.format
import br.com.alura.compras.android.googlefonts.montserratFontFamily
import br.com.alura.compras.android.iconColor
import br.com.alura.compras.android.models.Product
import br.com.alura.compras.android.samples.sampleProducts
import br.com.alura.compras.android.textColor
import br.com.alura.compras.android.uncheckedColor

@Composable
fun ProductItem(
    product: Product,
    onCheckedProductChange: (Product, Boolean) -> Unit,
    onDeleteProduct: (Product) -> Unit,
    onEditProduct: (Product, String) -> Unit,
    modifier: Modifier = Modifier,
    isEdittingMode: Boolean = false,
) {
    var editText by remember {
        mutableStateOf(product.name)
    }
    var isEdittingMode by remember(isEdittingMode) {
        mutableStateOf(isEdittingMode)
    }
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
                                    product,
                                    !product.wasBought,
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
                    if (isEdittingMode) {
                        val focusRequester = remember { FocusRequester() }
                        BasicTextField(
                            value = TextFieldValue(
                                text = editText, selection = TextRange(editText.length)
                            ),
                            onValueChange = {
                                editText = it.text
                            },
                            Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            textStyle = TextStyle(
                                fontFamily = montserratFontFamily,
                                color = textColor,
                                fontWeight = FontWeight(400),
                                fontSize = 18.sp
                            )
                        )
                        LaunchedEffect(null) {
                            focusRequester.requestFocus()
                        }
                    } else {
                        Text(
                            text = product.name, style = TextStyle(
                                textDecoration = textDecoration,
                                fontFamily = montserratFontFamily,
                                color = textColor,
                                fontSize = 18.sp
                            ),
                            fontWeight = FontWeight(600)
                        )
                    }
                }
                Row(verticalAlignment = Alignment.Top) {
                    if (isEdittingMode) {
                        Icon(
                            Icons.Filled.Done,
                            contentDescription = "done icon",
                            Modifier.clickable {
                                onEditProduct(
                                    product, editText
                                )
                                isEdittingMode = false
                            },
                            tint = iconColor
                        )
                    } else {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "delete icon",
                            Modifier.clickable {
                                onDeleteProduct(product)
                            },
                            tint = iconColor
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "edit icon",
                            Modifier.clickable {
                                isEdittingMode = true
                            },
                            tint = iconColor
                        )
                    }
                }
            }
            Text(
                text = product.dateTime.format(),
                style = TextStyle(
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
fun ProductItemPreview() {
    MyApplicationTheme {
        ProductItem(
            product = sampleProducts.random(),
            onCheckedProductChange = { _, _ -> },
            onDeleteProduct = {},
            onEditProduct = { _, _ -> }
        )
    }
}

@Preview
@Composable
fun ProductItemWithEdittingModeActivedPreview() {
    MyApplicationTheme {
        ProductItem(
            product = sampleProducts.random(),
            onCheckedProductChange = { _, _ -> },
            onDeleteProduct = {},
            onEditProduct = { _, _ -> },
            isEdittingMode = true
        )
    }
}
