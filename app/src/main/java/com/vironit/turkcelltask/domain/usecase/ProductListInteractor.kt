package com.vironit.turkcelltask.domain.usecase

import com.vironit.turkcelltask.Constants.IO_SCHEDULER
import com.vironit.turkcelltask.Constants.UI_SCHEDULER
import com.vironit.turkcelltask.domain.repository.DatabaseRepository
import com.vironit.turkcelltask.domain.repository.RestApiRepository
import com.vironit.turkcelltask.presentation.productlist.ProductListContact
import com.vironit.turkcelltask.presentation.productlist.ProductPresentModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class ProductListInteractor @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val apiRepository: RestApiRepository,
    @Named(UI_SCHEDULER) private val mainThread: Scheduler,
    @Named(IO_SCHEDULER) private val ioThread: Scheduler
) : ProductListContact.Interactor {

    override fun loadProductList(): Flowable<List<ProductPresentModel>> {
        return databaseRepository.getCachedProductList()
            .subscribeOn(ioThread)
            .map { list ->
                list.map {
                    ProductPresentModel(
                        it.id,
                        it.name,
                        it.price,
                        it.imageUrl,
                        it.description
                    )
                }
            }
            .observeOn(mainThread)
    }

    override fun updateProductList(): Completable {
        return apiRepository.loadProductList()
            .subscribeOn(ioThread)
            .doOnSuccess { databaseRepository.saveProductList(it) }
            .ignoreElement()
            .observeOn(mainThread)
    }
}