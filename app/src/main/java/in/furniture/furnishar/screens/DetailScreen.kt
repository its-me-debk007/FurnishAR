package `in`.furniture.furnishar.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import `in`.furniture.furnishar.SharedViewModel
import `in`.furniture.furnishar.ui.theme.ColorPrimary
import `in`.furniture.furnishar.ui.theme.Typography
import `in`.furniture.furnishar.ui.theme.WarningBackgroundColor

@Composable
fun DetailScreen(
    viewModel: SharedViewModel,
    onShowLoginSheet: () -> Unit,
    onBuy: (String, String, Int) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0XFFF3F6F8),
                        Color.Transparent
                    )
                )
            )
            .padding(start = 24.dp, end = 24.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = viewModel.data.type.toString(),
            style = Typography.body1,
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp,
            color = Color(0XFF171717).copy(alpha = 0.4f),
            modifier = Modifier.padding(top = 52.dp)
        )
        Text(
            text = viewModel.data.name.toString(),
            style = Typography.h1,
            fontSize = 30.sp,
            color = Color.Black,
        )
        Text(
            text = "from",
            style = Typography.body1,
            fontSize = 18.sp,
            color = Color(0XFF171717).copy(alpha = 0.4f),
        )
        Text(
            text = "₹ ${viewModel.data.price.toString()}",
            style = Typography.h1,
            fontSize = 22.sp,
            color = Color.Black,
        )
        AsyncImage(
            model = viewModel.data.imageUrl,
            contentDescription = "",
            modifier = Modifier
                .height(240.dp)
                .zIndex(1.1f)
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(Color.White)
                .padding(top = 32.dp, bottom = 24.dp, start = 4.dp, end = 4.dp)
        ) {
            Text(
                text = "Description",
                style = Typography.h1,
                fontSize = 18.sp,
                color = Color(0XFF171717)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = viewModel.data.description.toString(),
                style = Typography.body2,
                fontSize = 16.sp,
                color = Color(0XFF171717).copy(alpha = 0.5f),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ensure a well-lit environment and a large surface to render the AR Model!",
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = Color(0XFF171717).copy(alpha = 0.5f),
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .background(WarningBackgroundColor, RoundedCornerShape(2.dp))
                    .padding(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OutlinedButton(
                    onClick = {
                        try {
                            val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
                            val intentUri =
                                Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
                                    .appendQueryParameter("file", viewModel.data.link.toString())
                                    .appendQueryParameter("title", viewModel.data.name)
                                    .build()
                            sceneViewerIntent.data = intentUri
                            sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox")
                            sceneViewerIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            context.startActivity(sceneViewerIntent)

                        } catch (e: ActivityNotFoundException) {
                            Log.d("furture", e.message.toString())
                            viewModel.isARCoreDisabled = true
                        }
                    },
                    border = ButtonDefaults.outlinedBorder.copy(
                        brush = Brush.horizontalGradient(
                            listOf(ColorPrimary, ColorPrimary)
                        )
                    ),
                    elevation = ButtonDefaults.elevation(0.dp),
                    contentPadding = PaddingValues(vertical = 12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "View",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.7.sp,
                        color = ColorPrimary
                    )
                }

                Button(
                    onClick = {
                        if (!viewModel.isLoggedIn) {
                            onShowLoginSheet()
                            return@Button
                        }

                        onBuy(
                            viewModel.msisdn,
                            viewModel.data.name.toString(),
                            viewModel.data.price!!
                        )
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = viewModel.btnColor),
                    elevation = ButtonDefaults.elevation(0.dp),
                    contentPadding = PaddingValues(vertical = 12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Buy Now",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.7.sp,
                        color = Color.White
                    )
                }
            }
        }
    }

    if (viewModel.isARCoreDisabled) {
        ARCoreNotInstalledDialog(
            onDismiss = { viewModel.isARCoreDisabled = false },
            viewModel.btnColor
        )
    }
}

@Composable
fun ARCoreNotInstalledDialog(
    onDismiss: () -> Unit,
    btnColor: Color,
) {
    Dialog(onDismissRequest = {}) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(24.dp, 16.dp)
        ) {
            Text(
                text = "ARCore not found!",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )

            Text(
                text = "Your phone doesn't have ARCore installed. Kindly install for the AR Experience.",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    color = Color.DarkGray
                ),
                modifier = Modifier.padding(top = 16.dp, bottom = 12.dp)
            )

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Button(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = btnColor),
                    elevation = ButtonDefaults.elevation(0.dp),
                ) {
                    Text(
                        text = "OK",
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}