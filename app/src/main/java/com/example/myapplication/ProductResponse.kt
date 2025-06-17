package com.example.myapplication

data class ProductResponse(
    val count: Int, // Количество найденных продуктов
    val page: Int,  // Номер страницы
    val product: List<Product> // Массив продуктов
)

data class Product(
    val itemName: String,
    val itemModel: String,
    val itemUrl: String
)

data class BarcodeRequest(
    val barcode: String
)
