package `in`.furniture.furnishar.utils

import `in`.furniture.furnishar.models.FurnitureModel

fun getCategories() = listOf(
    FurnitureModel("chair", imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/ic_chair.png"),
    FurnitureModel("sofa", imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/ic_sofa.png"),
    FurnitureModel("home decor", imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/ic_home_decor.png"),
    FurnitureModel("office", imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/ic_office.png"),
    FurnitureModel("tables", imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/ic_table.png"),
)

fun getRecommended(): List<FurnitureModel> {

//    list.add(getChairs()[(0 until getChairs().size).random()])
//    list.add(getOffices()[(0 until getOffices().size).random()])
//    list.add(getSofas()[(0 until getSofas().size).random()])
//    list.add(getHomeDecors()[(0 until getHomeDecors().size).random()])
//    list.add(getTables()[(0 until getTables().size).random()])

    return listOf(
        FurnitureModel(
            "Orange Sheen Chair",
            imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/chair_orange.png",
            price = randomPrice(100, 200),
            description = randomDescription("Orange Sheen Chair"),
            link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/SheenChair/glTF/SheenChair.gltf",
            type = "chair"
        ),
        FurnitureModel(
            "Leather Sofa",
            imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa2.png",
            price = randomPrice(400, 1000),
            description = randomDescription("Leather Sofa"),
            type = "sofa",
            link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/sofas/sofa_game_ready__2k_pbr/scene.gltf"
        ),
        FurnitureModel(
            "Modern Pot",
            imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/dec1.png",
            price = randomPrice(20, 50),
            description = randomDescription("Modern Pot"),
            type = "home decoration",
            link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/decor/lavender_vase/scene.gltf"
        ),
        FurnitureModel(
            "Gaming Chair",
            imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/office.png",
            price = randomPrice(50, 80),
            description = randomDescription("Gaming Chair"),
            type = "office",
            link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/office%20essentials%20and%20cabinets/gaming_chair_1-_black/scene.gltf"
        ),
        FurnitureModel(
            "Table",
            imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/table1.png",
            price = randomPrice(50, 80),
            description = randomDescription("Table"),
            type = "table",
            link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/table/mahogany_table/scene.gltf"
        )
    )
}

fun getChairs() = listOf(
    FurnitureModel(
        "Modern Chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/chair_grey.png",
        price = randomPrice(100, 200),
        description = randomDescription("Modern Chair"),
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/chair/arm_chair__furniture/scene.gltf",
        type = "chair"
    ),
    FurnitureModel(
        "Manchester Chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/chair_old.png",
        price = randomPrice(100, 200),
        description = randomDescription("Manchester Chair"),
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/the_matrix_red_chesterfield_chair/scene.gltf",
        type = "chair"
    ),
    FurnitureModel(
        "Orange Sheen Chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/chair_orange.png",
        price = randomPrice(100, 200),
        description = randomDescription("Orange Sheen Chair"),
        link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/SheenChair/glTF/SheenChair.gltf",
        type = "chair"
    ),
    FurnitureModel(
        "Classic Chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/chair_wood.png",
        price = randomPrice(100, 200),
        description = randomDescription("Classic Chair"),
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/chair/furniture_for_real-time_visualization_engine/scene.gltf",
        type = "chair"
    )
)

fun getSofas() = listOf(
    FurnitureModel(
        "Velvet Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa1.png",
        price = randomPrice(400, 1000),
        description = randomDescription("Velvet Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/GlamVelvetSofa/glTF/GlamVelvetSofa.gltf"
    ),
    FurnitureModel(
        "Leather Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa2.png",
        price = randomPrice(400, 1000),
        description = randomDescription("Leather Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/sofas/sofa_game_ready__2k_pbr/scene.gltf"
    ),
    FurnitureModel(
        "Victorian Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa3.png",
        price = randomPrice(400, 1000),
        description = randomDescription("Victorian Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/victorian_lounge_sofa/scene.gltf"
    ),
    FurnitureModel(
        "Chesterfield Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa5.png",
        price = randomPrice(400, 1000),
        description = randomDescription("Chesterfield Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/sofa_02_4k.gltf/sofa_02_4k.gltf"
    ),
    FurnitureModel(
        "Modern Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa6.png",
        price = randomPrice(400, 1000),
        description = randomDescription("Modern Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/sofa_6/scene.gltf"
    ),
    FurnitureModel(
        "Lawson Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa8.png",
        price = randomPrice(400, 1000),
        description = randomDescription("Lawson Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/sofas/sofa/scene.gltf"
    )
)

fun randomDescription(type: String): String {
    return "This $type is an elegant and functional piece of furniture. It is suitable for family visits and parties with friends and perfect for relaxing in front of the TV after hard work."
}

fun randomPrice(lowerPrice: Int, upperPrice: Int): Int {
    return (lowerPrice..upperPrice).random()
}

fun getHomeDecors() = listOf(
    FurnitureModel(
        "Modern Pot",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/dec1.png",
        price = randomPrice(20, 50),
        description = randomDescription("Modern Pot"),
        type = "home decoration",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/decor/lavender_vase/scene.gltf"
    ),
    FurnitureModel(
        "Lamp",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/dec2.png",
        price = randomPrice(20, 50),
        description = randomDescription("Lamp"),
        type = "home decoration",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/decor/lamp/scene.gltf"
    ),
    FurnitureModel(
        "Flowers",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/dec3.png",
        price = randomPrice(20, 50),
        description = randomDescription("Flowers"),
        type = "home decoration",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/decor/flower_vase%20(1)/scene.gltf"
    )
)

fun getOfficeFurnitures() = listOf(
    FurnitureModel(
        "Gaming Chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/office.png",
        price = randomPrice(50, 80),
        description = randomDescription("Gaming Chair"),
        type = "office",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/office%20essentials%20and%20cabinets/gaming_chair_1-_black/scene.gltf"
    ),
    FurnitureModel(
        "Classic chair ",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/office_chair.png",
        price = randomPrice(50, 80),
        description = randomDescription("Classic chair "),
        type = "office",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/office%20essentials%20and%20cabinets/office_chair%20(1)/scene.gltf"
    ),
    FurnitureModel(
        "Desk",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/office_desk.png",
        price = randomPrice(50, 80),
        description = randomDescription("Desk"),
        type = "office",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/office%20essentials%20and%20cabinets/modern_table_set/scene.gltf"
    )
)

fun getTables() = listOf(
    FurnitureModel(
        "Table",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/table1.png",
        price = randomPrice(50, 80),
        description = randomDescription("Table"),
        type = "table",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/table/mahogany_table/scene.gltf"
    ),
    FurnitureModel(
        "Wooden Table",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/table2.png",
        price = randomPrice(50, 80),
        description = randomDescription("Wooden Table"),
        type = "table",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/office%20essentials%20and%20cabinets/simple_desk_free/scene.gltf"
    ),
    FurnitureModel(
        "Metal Table",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/table3.png",
        price = randomPrice(50, 80),
        description = randomDescription("Metal Table"),
        type = "table",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/table/industrial_table/scene.gltf"
    ),
    FurnitureModel(
        "Classic Table",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/table4.png",
        price = randomPrice(50, 80),
        description = randomDescription("Classic Table"),
        type = "table",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/table/victorian_coffee_table/scene.gltf"
    )
)

fun getSize(idx: Int): Int {
    return when (idx) {
        0 -> getChairs().size
        1 -> getSofas().size
        2 -> getHomeDecors().size
        3 -> getOfficeFurnitures().size
        4 -> getTables().size
        else -> 0
    }
}

