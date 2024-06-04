package `in`.furniture.furnishar.utils

import `in`.furniture.furnishar.models.FurnitureModel

fun getRecommended(): List<FurnitureModel> = listOf(
    FurnitureModel(
        "Orange Sheen Chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/chair_orange.png",
        price = generatePrice(2500, 4999),
        description = generateDescription("Orange Sheen Chair"),
        link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/SheenChair/glTF/SheenChair.gltf",
        type = "chair"
    ),
    FurnitureModel(
        "Leather Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa2.png",
        price = generatePrice(8000, 9999),
        description = generateDescription("Leather Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/sofas/sofa_game_ready__2k_pbr/scene.gltf"
    ),
    FurnitureModel(
        "Modern Pot",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/ic_home_decor.png",
        price = generatePrice(200, 500),
        description = generateDescription("Modern Pot"),
        type = "home decoration",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/decor/lavender_vase/scene.gltf"
    ),
    FurnitureModel(
        "Gaming Chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/office.png",
        price = generatePrice(4000, 9000),
        description = generateDescription("Gaming Chair"),
        type = "office",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/office%20essentials%20and%20cabinets/gaming_chair_1-_black/scene.gltf"
    ),
    FurnitureModel(
        "Table",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/table1.png",
        price = generatePrice(1000, 6000),
        description = generateDescription("Table"),
        type = "table",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/table/mahogany_table/scene.gltf"
    )
)

fun getChairs() = listOf(
    FurnitureModel(
        "Modern Chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/chair_grey.png",
        price = generatePrice(900, 2500),
        description = generateDescription("Modern Chair"),
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/chair/arm_chair__furniture/scene.gltf",
        type = "chair"
    ),
    FurnitureModel(
        "Manchester Chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/chair_old.png",
        price = generatePrice(5000, 9000),
        description = generateDescription("Manchester Chair"),
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/the_matrix_red_chesterfield_chair/scene.gltf",
        type = "chair"
    ),
    FurnitureModel(
        "Orange Sheen Chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/chair_orange.png",
        price = generatePrice(3000, 5000),
        description = generateDescription("Orange Sheen Chair"),
        link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/SheenChair/glTF/SheenChair.gltf",
        type = "chair"
    ),
    FurnitureModel(
        "Classic Chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/chair_wood.png",
        price = generatePrice(3000, 5000),
        description = generateDescription("Classic Chair"),
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/chair/furniture_for_real-time_visualization_engine/scene.gltf",
        type = "chair"
    )
)

fun getSofas() = listOf(
    FurnitureModel(
        "Velvet Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa1.png",
        price = generatePrice(9990, 50100),
        description = generateDescription("Velvet Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/GlamVelvetSofa/glTF/GlamVelvetSofa.gltf"
    ),
    FurnitureModel(
        "Leather Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa2.png",
        price = generatePrice(10000, 12000),
        description = generateDescription("Leather Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/sofas/sofa_game_ready__2k_pbr/scene.gltf"
    ),
    FurnitureModel(
        "Victorian Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa3.png",
        price = generatePrice(10000, 12000),
        description = generateDescription("Victorian Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/victorian_lounge_sofa/scene.gltf"
    ),
    FurnitureModel(
        "Chesterfield Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa5.png",
        price = generatePrice(10000, 12000),
        description = generateDescription("Chesterfield Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/sofa_02_4k.gltf/sofa_02_4k.gltf"
    ),
    FurnitureModel(
        "Modern Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa6.png",
        price = generatePrice(8000, 9999),
        description = generateDescription("Modern Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/sofa_6/scene.gltf"
    ),
    FurnitureModel(
        "Lawson Sofa",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/sofa8.png",
        price = generatePrice(6000, 10000),
        description = generateDescription("Lawson Sofa"),
        type = "sofa",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/sofas/sofa/scene.gltf"
    )
)

fun getHomeDecors() = listOf(
    FurnitureModel(
        "Modern Pot",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/ic_home_decor.png",
        price = generatePrice(999, 2000),
        description = generateDescription("Modern Pot"),
        type = "home decoration",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/decor/lavender_vase/scene.gltf"
    ),
    FurnitureModel(
        "Lamp",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/dec2.png",
        price = generatePrice(200, 800),
        description = generateDescription("Lamp"),
        type = "home decoration",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/decor/lamp/scene.gltf"
    ),
    FurnitureModel(
        "Flowers",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/dec3.png",
        price = generatePrice(200, 500),
        description = generateDescription("Flowers"),
        type = "home decoration",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/decor/flower_vase%20(1)/scene.gltf"
    )
)

fun getOfficeFurnitures() = listOf(
    FurnitureModel(
        "Gaming Chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/office.png",
        price = generatePrice(4000, 9000),
        description = generateDescription("Gaming Chair"),
        type = "office",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/office%20essentials%20and%20cabinets/gaming_chair_1-_black/scene.gltf"
    ),
    FurnitureModel(
        "Classic chair",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/office_chair.png",
        price = generatePrice(2000, 6000),
        description = generateDescription("Classic chair "),
        type = "office",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/office%20essentials%20and%20cabinets/office_chair%20(1)/scene.gltf"
    ),
    FurnitureModel(
        "Desk",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/office_desk.png",
        price = generatePrice(12000, 20000),
        description = generateDescription("Desk"),
        type = "office",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/office%20essentials%20and%20cabinets/modern_table_set/scene.gltf"
    )
)

fun getTables() = listOf(
    FurnitureModel(
        "Table",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/table1.png",
        price = generatePrice(3000, 6000),
        description = generateDescription("Table"),
        type = "table",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/table/mahogany_table/scene.gltf"
    ),
    FurnitureModel(
        "Wooden Table",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/table2.png",
        price = generatePrice(6000, 9999),
        description = generateDescription("Wooden Table"),
        type = "table",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/office%20essentials%20and%20cabinets/simple_desk_free/scene.gltf"
    ),
    FurnitureModel(
        "Metal Table",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/table3.png",
        price = generatePrice(6000, 9999),
        description = generateDescription("Metal Table"),
        type = "table",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/table/industrial_table/scene.gltf"
    ),
    FurnitureModel(
        "Classic Table",
        imageUrl = "https://raw.githubusercontent.com/its-me-debk007/FurnishAR/furniture-models/images/table4.png",
        price = generatePrice(6000, 8000),
        description = generateDescription("Classic Table"),
        type = "table",
        link = "https://raw.githubusercontent.com/Sachinbhola/App-Templates/master/Resources/table/victorian_coffee_table/scene.gltf"
    )
)

fun generateDescription(type: String): String = "Introducing our $type, a versatile and stylish addition to any space. Crafted with both elegance and functionality in mind, this chair is designed to elevate your gaming experience while seamlessly blending into your home decor."

fun generatePrice(lowerPrice: Int, upperPrice: Int): Int = (lowerPrice..upperPrice).random()