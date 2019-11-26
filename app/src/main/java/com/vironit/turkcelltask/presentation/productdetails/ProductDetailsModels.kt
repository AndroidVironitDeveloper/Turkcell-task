package com.vironit.turkcelltask.presentation.productdetails

data class ProductDetailsPresentModel(
    val name: String,
    val price: Int,
    val imageUrl: String,
    val description: String?
)