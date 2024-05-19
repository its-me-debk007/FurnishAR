package `in`.furniture.furnishar

import android.os.Bundle
import android.widget.Toast
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
import com.razorpay.Checkout
import com.razorpay.ExternalWalletListener
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import dagger.hilt.android.AndroidEntryPoint
import `in`.furniture.furnishar.screens.DetailScreen
import `in`.furniture.furnishar.screens.HomeScreen
import `in`.furniture.furnishar.screens.LoginBottomSheet
import `in`.furniture.furnishar.screens.SplashScreen
import `in`.furniture.furnishar.ui.theme.FurnishARTheme
import org.json.JSONObject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity(), PaymentResultWithDataListener, ExternalWalletListener {

    private lateinit var checkout: Checkout

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Checkout.preload(applicationContext)

        checkout = Checkout()
        checkout.setKeyID(BuildConfig.RAZORPAY_KEY)

        val options = JSONObject()
        options.apply {
            put("name", "Razorpay Corp")
            put("description", "Demoing Charges")
            put("image", "https://firebasestorage.googleapis.com/v0/b/furnishar-ar.appspot.com/o/ic_launcher-playstore.jpg?alt=media&token=12decb7f-02d8-4fb1-aec1-1951fc61d6ec")
            put("theme.color", "#8A49F7")
            put("currency", "INR")
            put("amount", "20000")
        }

        val retryObj = JSONObject()
        retryObj.put("enabled", true)
        retryObj.put("max_count", 4)

        options.put("retry", retryObj)

        val prefill = JSONObject()
        prefill.put("contact", "9118882517")

        options.put("prefill", prefill)

        checkout.open(this, options)

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

    override fun onPaymentSuccess(razorpayPaymentId: String?, paymentData: PaymentData?) {
        Toast.makeText(this, "Thanks for shopping with FurnishAR", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this, p0.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onExternalWalletSelected(p0: String?, p1: PaymentData?) {
        Toast.makeText(this, p0, Toast.LENGTH_SHORT).show()
    }
}