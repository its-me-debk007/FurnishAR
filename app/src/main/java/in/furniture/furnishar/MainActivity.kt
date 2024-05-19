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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.razorpay.Checkout
import com.razorpay.ExternalWalletListener
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import dagger.hilt.android.AndroidEntryPoint
import `in`.furniture.furnishar.components.SuccessFailureDialog
import `in`.furniture.furnishar.screens.DetailScreen
import `in`.furniture.furnishar.screens.HomeScreen
import `in`.furniture.furnishar.screens.LoginBottomSheet
import `in`.furniture.furnishar.screens.PaymentHistoryScreen
import `in`.furniture.furnishar.screens.SplashScreen
import `in`.furniture.furnishar.ui.theme.FurnishARTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), PaymentResultWithDataListener, ExternalWalletListener {

    private lateinit var checkout: Checkout
    private lateinit var onPaymentSuccessCallback: (() -> Unit)
    private lateinit var onPaymentErrorCallback: ((String) -> Unit)

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Checkout.preload(applicationContext)

        checkout = Checkout()
        checkout.setKeyID(BuildConfig.RAZORPAY_KEY)

        val razorpayOptions = JSONObject()

        val retryObj = JSONObject()
        retryObj.put("enabled", true)
        retryObj.put("max_count", 4)

        razorpayOptions.put("retry", retryObj)

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

                    LaunchedEffect(Unit) {
                        onPaymentSuccessCallback = {
                            viewModel.showPaymentSuccessDialog = true
                            scope.launch {
                                delay(3000L)
                                viewModel.showPaymentSuccessDialog = false
                            }
                        }

                        onPaymentErrorCallback = { errorMsg ->
                            viewModel.paymentFailureDialogMsg = errorMsg
                            scope.launch {
                                delay(3000L)
                                viewModel.paymentFailureDialogMsg = null
                            }
                        }
                    }

                    if (viewModel.showPaymentSuccessDialog) {
                        SuccessFailureDialog(
                            isSuccess = true,
                            heading = "Hooray! Payment Successful",
                            description = "Thank u for shopping with FurnishAR :)",
                        )
                    }

                    viewModel.paymentFailureDialogMsg?.let {
                        SuccessFailureDialog(
                            isSuccess = false,
                            heading = "Payment Failed",
                            description = it,
                        )
                    }

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
                                DetailScreen(
                                    viewModel = viewModel,
                                    onShowLoginSheet = {
                                        scope.launch { sheetState.show() }
                                    },
                                    onBuy = { msisdn: String, furnitureName: String, price: Int ->
                                        val prefill = JSONObject()
                                        prefill.put("email", "furnish.ar@gmail.com")
                                        prefill.put("contact", msisdn)
                                        razorpayOptions.put("prefill", prefill)

                                        razorpayOptions.apply {
                                            put("name", "+91 ${viewModel.msisdn}")
                                            put("description", "Payment for buying $furnitureName")
                                            put(
                                                "image",
                                                "https://firebasestorage.googleapis.com/v0/b/furnishar-ar.appspot.com/o/ic_launcher-playstore.jpg?alt=media&token=12decb7f-02d8-4fb1-aec1-1951fc61d6ec"
                                            )
                                            put("theme.color", "#8A49F7")
                                            put("currency", "INR")
                                            put("amount", price * 100)
                                        }

                                        checkout.open(this@MainActivity, razorpayOptions)
                                    }
                                )
                            }
                            composable("payment-history") {
                                PaymentHistoryScreen(
                                    navController = navController,
                                    viewModel = viewModel,
                                )
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

    override fun onPaymentSuccess(razorpayPaymentId: String?, paymentData: PaymentData?) {
        onPaymentSuccessCallback()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        onPaymentErrorCallback("Please try again to buy the furniture")
    }

    override fun onExternalWalletSelected(p0: String?, p1: PaymentData?) {}
}