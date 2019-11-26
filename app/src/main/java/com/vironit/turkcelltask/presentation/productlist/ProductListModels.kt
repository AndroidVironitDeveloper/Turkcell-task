package com.vironit.turkcelltask.presentation.productlist

data class ProductPresentModel(
    val id: String,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val description: String?
)