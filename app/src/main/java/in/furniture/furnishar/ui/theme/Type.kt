package `in`.furniture.furnishar.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import `in`.furniture.furnishar.R


val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Gilroy(),
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        color = colorBlack
    ),
    body1= TextStyle(
        fontFamily = Gilroy(),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = colorBlack
    ),
    body2 = TextStyle(
        fontFamily = Gilroy(),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = colorSecondaryText
    ),
    defaultFontFamily = Gilroy()
)


fun Gilroy() = FontFamily(
    Font(R.font.font_gilroy_bold, FontWeight.Bold),
    Font(R.font.font_gilroy_medium, FontWeight.Medium),
    Font(R.font.font_gilroy_semi_bold, FontWeight.SemiBold),
)