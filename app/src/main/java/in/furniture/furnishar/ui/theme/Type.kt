package `in`.furniture.furnishar.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import `in`.furniture.furnishar.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val googleFont = GoogleFont("Lato")

val Lato = FontFamily(
    Font(googleFont = googleFont, fontProvider = provider),
    Font(googleFont = googleFont, fontProvider = provider, style = FontStyle.Italic),
    Font(googleFont = googleFont, fontProvider = provider, FontWeight.Bold),
    Font(googleFont = googleFont, fontProvider = provider, FontWeight.Medium),
    Font(googleFont = googleFont, fontProvider = provider, FontWeight.SemiBold),
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = Color.Black
    ),
    body1 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.Black
    ),
    body2 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = ColorSecondaryText
    ),
    defaultFontFamily = Lato
)