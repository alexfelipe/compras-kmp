package br.com.alura.compras.android.samples

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.alura.compras.android.models.Product
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

val sampleProducts = List(5) { index ->
    Product(
        name = LoremIpsum(Random.nextInt(1, 10)).values.first(),
        wasBought = index.mod(2) == 0,
        dateTime = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}
