package `in`.furniture.furnishar.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import `in`.furniture.furnishar.SharedViewModel
import `in`.furniture.furnishar.ui.theme.Typography
import `in`.furniture.furnishar.ui.theme.WarningBackgroundColor
import okhttp3.internal.toHexString

@Composable
fun DetailScreen(viewModel: SharedViewModel) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(viewModel.data.imageUrl)
            .allowHardware(false)
            .build()
        val result = loader.execute(request)

        val bitmap: Bitmap? = if (result is SuccessResult) {
            (result.drawable as BitmapDrawable).bitmap
        } else null

        bitmap?.let {
            Palette.from(it).generate { palette ->
                kotlin.runCatching {
                    val hexColor = palette?.vibrantSwatch?.rgb?.toHexString()
                    hexColor?.let {
                        viewModel.btnColor = getColor(hexColor)
                    }
                }.getOrElse {
                    val hexColor = palette?.darkMutedSwatch?.rgb?.toHexString()
                    hexColor?.let {
                        viewModel.btnColor = getColor(hexColor)
                    }
                }
            }
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color(0XFFF3F6F8)),
        constraintSet = constraintsDetail()
    ) {
        Text(
            text = viewModel.data.type.toString(),
            style = Typography.body1,
            fontSize = 18.sp,
            color = Color(0XFF171717).copy(alpha = 0.4f),
            modifier = Modifier.layoutId("tvType")
        )
        Text(
            text = viewModel.data.name.toString().lowercase(),
            style = Typography.h1,
            fontSize = 32.sp,
            color = Color.Black,
            modifier = Modifier.layoutId("tvName")
        )
        Text(
            text = "from",
            style = Typography.body1,
            fontSize = 18.sp,
            color = Color(0XFF171717).copy(alpha = 0.4f),
            modifier = Modifier.layoutId("tvFrom")
        )
        Text(
            text = "₹ ${viewModel.data.price.toString()}",
            style = Typography.h1,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.layoutId("tvPrice")
        )
        AsyncImage(
            model = viewModel.data.imageUrl,
            contentDescription = "",
            modifier = Modifier
                .height(240.dp)
                .zIndex(1.1f)
                .layoutId("ivImg")
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(Color.White)
                .layoutId("clDetail")
                .padding(24.dp, 160.dp, 24.dp, 24.dp)
        ) {
            Text(
                text = viewModel.data.name.toString(),
                style = Typography.h1,
                fontSize = 18.sp,
                color = Color(0XFF171717).copy(alpha = 1f)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = viewModel.data.description.toString().lowercase(),
                style = Typography.body2,
                fontSize = 16.sp,
                color = Color(0XFF171717).copy(alpha = 0.5f),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ensure a well-lit environment and a large surface to render the AR Model!",
                style = Typography.body2,
                fontSize = 14.sp,
                color = Color(0XFF171717).copy(alpha = 0.5f),
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .background(WarningBackgroundColor, RoundedCornerShape(8.dp))
                    .padding(12.dp)
            )

            Button(
                onClick = {
                    try {
                        val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
                        val intentUri =
                            Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
                                .appendQueryParameter("file", viewModel.data.link.toString())
//                                .appendQueryParameter("mode", "ar_only")
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
                colors = ButtonDefaults.buttonColors(backgroundColor = viewModel.btnColor),
                elevation = ButtonDefaults.elevation(0.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "View in your House!",
                    style = Typography.body1,
                    fontSize = 18.sp,
                    color = Color.White
                )
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

fun constraintsDetail(): ConstraintSet = ConstraintSet {
    val tvType = createRefFor("tvType")
    val tvName = createRefFor("tvName")
    val tvFrom = createRefFor("tvFrom")
    val tvPrice = createRefFor("tvPrice")
    val ivImg = createRefFor("ivImg")
    val clDetail = createRefFor("clDetail")

    constrain(tvType) {
        top.linkTo(parent.top, 64.dp)
        start.linkTo(parent.start, 24.dp)
    }
    constrain(tvName) {
        top.linkTo(tvType.bottom, 4.dp)
        start.linkTo(parent.start, 24.dp)
    }
    constrain(tvFrom) {
        top.linkTo(tvName.bottom, 24.dp)
        start.linkTo(parent.start, 24.dp)
    }
    constrain(tvPrice) {
        top.linkTo(tvFrom.bottom, 4.dp)
        start.linkTo(parent.start, 24.dp)
    }
    constrain(ivImg) {
        top.linkTo(clDetail.top, 32.dp)
        bottom.linkTo(clDetail.top, 32.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.fillToConstraints
    }
    constrain(clDetail) {
        top.linkTo(tvPrice.bottom, 80.dp)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }


}

fun getColor(colorString: String): Color {
    return Color(android.graphics.Color.parseColor("#$colorString"))
}