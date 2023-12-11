package `in`.furniture.furnishar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.furniture.furnishar.models.FurnitureModel
import `in`.furniture.furnishar.utils.getRecommended
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    val furnitureModels = getRecommended()
    var data = FurnitureModel()

}