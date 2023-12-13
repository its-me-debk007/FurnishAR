package `in`.furniture.furnishar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.furniture.furnishar.models.FurnitureModel
import `in`.furniture.furnishar.ui.theme.colorPurple
import `in`.furniture.furnishar.utils.getRecommended
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    val furnitureModels = getRecommended()
    var data = FurnitureModel()

    var isARCoreNotInstalled by mutableStateOf(false)
    var btnColor by mutableStateOf(colorPurple)
}