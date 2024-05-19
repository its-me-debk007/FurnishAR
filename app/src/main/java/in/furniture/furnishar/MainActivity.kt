package `in`.furniture.furnishar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import `in`.furniture.furnishar.screens.DetailScreen
import `in`.furniture.furnishar.screens.HomeScreen
import `in`.furniture.furnishar.screens.LoginBottomSheet
import `in`.furniture.furnishar.screens.SplashScreen
import `in`.furniture.furnishar.ui.theme.FurnishARTheme
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FurnishARTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val viewModel = hiltViewModel<SharedViewModel>()
                    val sheetState = rememberModalBottomSheetState(
                        initialValue = ModalBottomSheetValue.Hidden
                    )
                    val scope = rememberCoroutineScope()

                    ModalBottomSheetLayout(
                        sheetState = sheetState,
                        sheetContent = {
                            LoginBottomSheet(
                                sheetState = sheetState,
                                viewModel = viewModel,
                            )
                        }
                    ) {
                        NavHost(navController = navController, startDestination = "splash") {
                            composable("home") { HomeScreen(navController, viewModel) }
                            composable("detail") {
                                DetailScreen(viewModel = viewModel, onShowLoginSheet = {
                                    scope.launch { sheetState.show() }
                                })
                            }
                            composable("splash") {
                                SplashScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}