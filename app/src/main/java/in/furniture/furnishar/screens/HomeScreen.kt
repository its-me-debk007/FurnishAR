package `in`.furniture.furnishar.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import `in`.furniture.furnishar.R
import `in`.furniture.furnishar.SharedViewModel
import `in`.furniture.furnishar.models.FurnitureModel
import `in`.furniture.furnishar.ui.theme.ColorPrimary

@Composable
fun HomeScreen(navController: NavHostController, viewModel: SharedViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = buildAnnotatedString {
                            append("Furnish")
                            withStyle(SpanStyle(color = ColorPrimary)) {
                                append("AR")
                            }
                        },
                        style = `in`.furniture.furnishar.ui.theme.Typography.h1,
                        modifier = Modifier.offset(x = (-10).dp)
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = null,
                        tint = ColorPrimary,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(32.dp)
                    )
                },
                elevation = 0.dp,
                modifier = Modifier.padding(top = 40.dp, bottom = 8.dp)
            )
        }
    ) {

        LazyColumn(
            contentPadding = PaddingValues(
                top = it.calculateTopPadding(),
                bottom = 24.dp
            )
        ) {
            items(viewModel.sections.size) {
                Column {
                    Text(
                        text = viewModel.sections[it].first,
                        style = `in`.furniture.furnishar.ui.theme.Typography.h1,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = 24.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp)
                    ) {
                        itemsIndexed(viewModel.sections[it].second) { _, category ->
                            FurnitureItem(furnitureModel = category) {
                                viewModel.data = category
                                navController.navigate("detail")
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

    }
}

@Composable
fun FurnitureItem(
    furnitureModel: FurnitureModel,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.clickable { onClick() },
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_card_back),
            contentDescription = null,
            modifier = Modifier.width(170.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = furnitureModel.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(170.dp)
                    .padding(horizontal = 6.dp)
            )
            Text(
                text = furnitureModel.name ?: "",
                style = `in`.furniture.furnishar.ui.theme.Typography.h1,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "₹${furnitureModel.price}",
                style = `in`.furniture.furnishar.ui.theme.Typography.h1,
                fontSize = 12.sp,
                color = Color(0xFF171717).copy(alpha = 0.4f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}

fun constraints(): ConstraintSet {
    return ConstraintSet {
        val ivBack = createRefFor("ic_back")
        val ivImg = createRefFor("ic_img")
        val tvName = createRefFor("tv_name")
        val tvSize = createRefFor("tv_size")

        constrain(ivBack) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(ivImg) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom, 64.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }
        constrain(tvName) {
            bottom.linkTo(parent.bottom, 40.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }
        constrain(tvSize) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            top.linkTo(tvName.bottom, 4.dp)
        }
    }
}