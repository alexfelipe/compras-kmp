package br.com.alura.compras.android.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.compras.android.MyApplicationTheme
import br.com.alura.compras.android.googlefonts.mansalvaFontFamily
import br.com.alura.compras.android.mainColor
import kotlin.random.Random

@Composable
fun ProductsDivider(
    title: String
) {
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

@Preview
@Composable
fun ProductsDividerPreview() {
    MyApplicationTheme {
        ProductsDivider(
            title =
            LoremIpsum(Random.nextInt(1, 3)).values.first()
        )
    }
}