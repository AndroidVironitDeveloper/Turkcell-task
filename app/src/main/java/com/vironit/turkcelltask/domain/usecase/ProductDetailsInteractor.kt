package com.vironit.turkcelltask.domain.usecase

import com.vironit.turkcelltask.Constants
import com.vironit.turkcelltask.domain.repository.RestApiRepository
import com.vironit.turkcelltask.presentation.productdetails.ProductDetailsContact
import com.vironit.turkcelltask.presentation.productdetails.ProductDetailsPresentModel
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class ProductDetailsInteractor @Inject constructor(
    private val apiRepository: RestApiRepository,
    @Named(Constants.UI_SCHEDULER) private val mainThread: Scheduler,
    @Named(Constants.IO_SCHEDULER) private val ioThread: Scheduler
) : ProductDetailsContact.Interactor {

    override fun loadProductDetails(id: String): Single<ProductDetailsPresentModel> {
        return apiRepository.loadProductDetails(id)
            .subscribeOn(ioThread)
            .map { ProductDetailsPresentModel(it.name, it.price, it.imageUrl, it.description) }
            .observeOn(mainThread)
    }
}