package com.vironit.data.api

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {

    @GET("cart/list")
    fun loadProductList(): Single<ProductList>

    @GET("cart/{id}/detail")
    fun loadProductDetails(@Path("id") id: String): Single<Product>
}

data class Product(
    @SerializedName("product_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("description")
    val description: String?
)

data class ProductList(
    @SerializedName("products")
    val products: List<Product>
)