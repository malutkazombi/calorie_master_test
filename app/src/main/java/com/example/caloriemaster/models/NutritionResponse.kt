package com.example.caloriemaster

data class NutritionResponse(
    val hits: List<Hit>
)

data class Hit(
    val fields: Fields
)

data class Fields(
    val item_name: String,
    val brand_name: String,
    val nf_calories: Double,
    val nf_total_fat: Double,
    val nf_protein: Double,
    val nf_total_carbohydrate: Double
)
