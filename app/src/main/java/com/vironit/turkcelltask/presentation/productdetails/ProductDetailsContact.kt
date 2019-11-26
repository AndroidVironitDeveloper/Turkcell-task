package com.vironit.turkcelltask.presentation.productdetails

import io.reactivex.Single

interface ProductDetailsContact {

    interface Interactor {
        fun loadProductDetails(id: String): Single<ProductDetailsPresentModel>
    }
}