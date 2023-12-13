package `in`.furniture.furnishar.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavHostController
import `in`.furniture.furnishar.R
import `in`.furniture.furnishar.SharedViewModel
import `in`.furniture.furnishar.models.FurnitureModel
import `in`.furniture.furnishar.ui.theme.colorPurple
import `in`.furniture.furnishar.utils.getCategories
import `in`.furniture.furnishar.utils.getChairs
import `in`.furniture.furnishar.utils.getSize

@Composable
fun HomeScreen(navController: NavHostController, viewModel: SharedViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color.White)
            .padding(top = 40.dp, bottom = 32.dp)
    ) {
        Row(
            modifier = Modifier.padding(top = 8.dp, start = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "null",
                tint = colorPurple,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "Furnish",
                modifier = Modifier.padding(start = 8.dp),
                style = `in`.furniture.furnishar.ui.theme.Typography.h1,
            )
            Text(
                text = "AR",
                color = colorPurple,
                fontWeight = FontWeight.Bold,
                style = `in`.furniture.furnishar.ui.theme.Typography.h1
            )
        }

        Text(
            text = "Recommended for you...",
            style = `in`.furniture.furnishar.ui.theme.Typography.h1,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 32.dp, start = 24.dp, end = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        ) {
            itemsIndexed(viewModel.furnitureModels) { idx, category ->
                Cards(category = category) {
                    if (idx == 0) {
                        viewModel.data = category
                        navController.navigate("detail")
                    }
                }
            }
        }

        Text(
            text = "Browse by Categories",
            style = `in`.furniture.furnishar.ui.theme.Typography.h1,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 32.dp, start = 24.dp)
        )

        Spacer(modifier = Modifier.height(64.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        ) {
            itemsIndexed(getCategories()) { idx, category ->
                CategoryCard(index = idx, category = category)
            }
        }

        Text(
            text = "Chairs",
            style = `in`.furniture.furnishar.ui.theme.Typography.h1,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 32.dp, start = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        ) {
            itemsIndexed(getChairs()) { _, category ->
                Cards(category = category) {}
            }
        }

//        Text(
//            text = "sofas",
//            style = `in`.furniture.furnishar.ui.theme.Typography.h1,
//            fontSize = 18.sp,
//            modifier = Modifier.padding(top = 32.dp, start = 24.dp)
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
//        ) {
//            itemsIndexed(getSofas()) { _, category ->
//                Cards(category = category) {}
//            }
//        }
//
//        Text(
//            text = "home decors",
//            style = `in`.furniture.furnishar.ui.theme.Typography.h1,
//            fontSize = 18.sp,
//            modifier = Modifier.padding(top = 32.dp, start = 24.dp)
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
//        ) {
//            itemsIndexed(getHomeDecors()) { idx, category ->
//                Cards(category = category) {}
//            }
//        }
//
//        Text(
//            text = "office furniture",
//            style = `in`.kay.furture.ui.theme.Typography.h1,
//            fontSize = 18.sp,
//            modifier = Modifier.padding(top = 32.dp, start = 24.dp)
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
//        ) {
//            itemsIndexed(getOffices()) { idx, category ->
//                Cards(
//                    index = idx,
//                    category = category,
//                    navController = navController,
//                    viewModel = viewModel
//                )
//            }
//        }
//        Text(
//            text = "tables",
//            style = `in`.kay.furture.ui.theme.Typography.h1,
//            fontSize = 18.sp,
//            modifier = Modifier.padding(top = 32.dp, start = 24.dp)
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
//        ) {
//            itemsIndexed(getTables()) { idx, category ->
//                Cards(
//                    index = idx,
//                    category = category,
//                    navController = navController,
//                    viewModel = viewModel
//                )
//            }
//        }
    }
}

@Composable
fun Cards(
    category: FurnitureModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF3F6F8))
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = category.drawable),
            contentDescription = null,
            modifier = Modifier
                .size(145.dp)
                .padding(8.dp)
        )
        Text(
            text = category.name ?: "null",
            style = `in`.furniture.furnishar.ui.theme.Typography.h1,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = "₹${category.price}",
            style = `in`.furniture.furnishar.ui.theme.Typography.h1,
            fontSize = 12.sp,
            color = Color(0xFF171717).copy(alpha = 0.4f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
    }
}

@Composable
fun CategoryCard(category: FurnitureModel, index: Int) {
    val padding: Dp = when {
        category.name.equals("home decor") -> 40.dp
        category.name.equals("office") -> 48.dp
        else -> 32.dp
    }

    ConstraintLayout(
        modifier = Modifier
            .height(200.dp)
            .width(205.dp),
        constraintSet = constraints()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_card_back),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .layoutId("ic_back")
        )

        Image(
            painter = painterResource(id = category.drawable),
            contentDescription = null,
            modifier = Modifier
                .layoutId("ic_img")
                .size(205.dp)
                .padding(bottom = padding),
            alignment = Alignment.TopCenter
        )

        Text(
            text = category.name.toString(),
            style = `in`.furniture.furnishar.ui.theme.Typography.h1,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.layoutId("tv_name")
        )

        Text(
            text = (getSize(index) - 1).toString() + "+ products",
            style = `in`.furniture.furnishar.ui.theme.Typography.h1,
            fontSize = 12.sp,
            color = Color(0xFF171717).copy(alpha = 0.4f),
            textAlign = TextAlign.Center,
            modifier = Modifier.layoutId("tv_size")
        )
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