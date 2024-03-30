package `in`.furniture.furnishar.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import `in`.furniture.furnishar.ui.theme.ColorPrimary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val slow = 700
    val fast = 300
    var isAnimStart by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit, block = {
        delay(500L)
        isAnimStart = true
        delay(1200L)
        navController.popBackStack()
        navController.navigate("home")
    })

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPrimary),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.ShoppingCart,
            contentDescription = "null",
            tint = Color.White,
            modifier = Modifier.size(32.dp)
        )
        AnimatedVisibility(
            visible = isAnimStart,
            enter = fadeIn(
                animationSpec = tween(durationMillis = fast)
            ) + expandHorizontally(
                expandFrom = Alignment.End,
                animationSpec = tween(
                    durationMillis = slow,
                    easing = FastOutLinearInEasing,
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = slow,
                    easing = FastOutLinearInEasing,
                )
            ) + shrinkHorizontally(
                shrinkTowards = Alignment.End,
                animationSpec = tween(
                    durationMillis = slow,
                    easing = FastOutLinearInEasing,
                )
            )
        ) {
            Row(modifier = Modifier.padding(start = 4.dp)) {
                Text(
                    text = "Fur",
                    color = Color.White,
                    modifier = Modifier.padding(start = 0.dp),
                    style = `in`.furniture.furnishar.ui.theme.Typography.h1
                )
                Text(
                    text = "nish",
                    color = Color.White,
                    style = `in`.furniture.furnishar.ui.theme.Typography.h1
                )
                Text(
                    text = "AR",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = `in`.furniture.furnishar.ui.theme.Typography.h1
                )
            }
        }
    }
}

