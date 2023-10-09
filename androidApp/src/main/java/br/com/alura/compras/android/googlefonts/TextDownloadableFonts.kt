package br.com.alura.compras.android.googlefonts

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import br.com.alura.compras.android.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val mansalvaFont = GoogleFont("Mansalva")
val montserratFont = GoogleFont("Montserrat")

val mansalvaFontFamily = FontFamily(
    Font(googleFont = mansalvaFont, fontProvider = provider)
)

val montserratFontFamily = FontFamily(
    Font(googleFont = montserratFont, fontProvider = provider)
)