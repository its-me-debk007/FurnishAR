package `in`.furniture.furnishar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.furniture.furnishar.models.FurnitureModel
import `in`.furniture.furnishar.ui.theme.ColorPrimary
import `in`.furniture.furnishar.utils.getChairs
import `in`.furniture.furnishar.utils.getHomeDecors
import `in`.furniture.furnishar.utils.getOfficeFurnitures
import `in`.furniture.furnishar.utils.getRecommended
import `in`.furniture.furnishar.utils.getSofas
import `in`.furniture.furnishar.utils.getTables
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    var verificationId: String? by mutableStateOf(null)
    var isARCoreDisabled by mutableStateOf(false)
    var btnColor by mutableStateOf(ColorPrimary)

    var msisdn by mutableStateOf("9118882517")
    var otp by mutableStateOf("")

    var isLoginSheetLoading by mutableStateOf(false)
    var isLoggedIn by mutableStateOf(false)
    var loginErrorMsg: String? by mutableStateOf(null)

    var data = FurnitureModel()

    var sections = listOf(
        "Recommended for you..." to getRecommended().shuffled(),
        "Chairs" to getChairs().shuffled(),
        "Sofas" to getSofas().shuffled(),
        "Home decors" to getHomeDecors().shuffled(),
        "Office furniture" to getOfficeFurnitures().shuffled(),
        "Tables" to getTables().shuffled()
    )
}