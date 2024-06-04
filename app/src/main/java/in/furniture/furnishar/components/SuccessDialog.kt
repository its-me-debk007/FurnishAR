package `in`.furniture.furnishar.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import `in`.furniture.furnishar.R

@Composable
fun SuccessFailureDialog(
    isSuccess: Boolean,
    heading: String,
    description: String,
    modifier: Modifier = Modifier
) {
    val icon = if (isSuccess) R.drawable.ic_success else R.drawable.ic_failure

    Dialog(onDismissRequest = {}) {
        Column(
            modifier
                .background(Color.White, RoundedCornerShape(18.dp))
                .padding(32.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .background(Color.White, CircleShape)
            )

            Text(
                text = heading,
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 29.sp,
                    fontWeight = FontWeight(700),
                    color = Color.Black,
                    letterSpacing = 0.5.sp,
                ),
                modifier = Modifier.padding(top = 14.dp, bottom = 12.dp)
            )

            Text(
                text = description,
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    color = Color.DarkGray,
                    letterSpacing = 0.5.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}