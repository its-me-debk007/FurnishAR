package `in`.furniture.furnishar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.furniture.furnishar.models.FurnitureModel
import `in`.furniture.furnishar.ui.theme.ColorPrimary
import `in`.furniture.furnishar.utils.getCategories
import `in`.furniture.furnishar.utils.getChairs
import `in`.furniture.furnishar.utils.getHomeDecors
import `in`.furniture.furnishar.utils.getOfficeFurnitures
import `in`.furniture.furnishar.utils.getRecommended
import `in`.furniture.furnishar.utils.getSofas
import `in`.furniture.furnishar.utils.getTables
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    var data = FurnitureModel()

    var isARCoreDisabled by mutableStateOf(false)

    var btnColor by mutableStateOf(ColorPrimary)

    var sections = listOf(
        "Recommended for you..." to getRecommended(),
        "Browse by Categories" to getCategories(),
        "Chairs" to getChairs(),
        "Sofas" to getSofas(),
        "Home decors" to getHomeDecors(),
        "Office furniture" to getOfficeFurnitures(),
        "Tables" to getTables()
    )
}