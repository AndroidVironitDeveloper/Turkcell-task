package com.vironit.turkcelltask.domain.repository

import com.vironit.data.api.Product
import io.reactivex.Flowable
import io.reactivex.Observable

interface DatabaseRepository {
    fun saveProductList(list: List<Product>)
    fun getCachedProductList(): Flowable<List<Product>>
}