package `in`.furniture.furnishar.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FurnitureModel(
    var name: String? = null,
    val imageUrl: String = "",
    val link: String? = null,
    val price : Int? = 0,
    val description : String ? = "",
    val type:String? = ""
) : Parcelable
