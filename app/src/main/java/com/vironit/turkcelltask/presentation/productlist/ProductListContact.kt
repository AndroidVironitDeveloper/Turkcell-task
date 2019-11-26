package com.vironit.turkcelltask.presentation.productlist

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface ProductListContact {

    interface Interactor {
        fun loadProductList(): Flowable<List<ProductPresentModel>>
        fun updateProductList(): Completable
    }
}