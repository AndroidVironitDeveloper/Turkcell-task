package com.vironit.turkcelltask.presentation.productlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vironit.turkcelltask.App
import com.vironit.turkcelltask.Constants.APP_LOG
import com.vironit.turkcelltask.domain.usecase.ProductListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductListViewModel (app: Application) : AndroidViewModel(app) {

    val productListLiveData = MutableLiveData<List<ProductPresentModel>>()

    private lateinit var dbDisposable: Disposable
    private lateinit var apiDisposable: Disposable

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var productListInteractor: ProductListContact.Interactor

    fun getProductList() {
        dbDisposable =
            productListInteractor.loadProductList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { list -> productListLiveData.postValue(list) },
                    { error -> Log.e(APP_LOG, error.localizedMessage ?: "") }
                )

    }

    fun updateProductList() {
        apiDisposable = productListInteractor.updateProductList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d(APP_LOG, "Product list updated") },
                { error -> Log.e(APP_LOG, error.localizedMessage ?: "") })
    }

    fun releaseOnStopResources() {
        if (!apiDisposable.isDisposed) apiDisposable.dispose()
    }

    fun releaseOnDestroyResources() {
        if (!dbDisposable.isDisposed) dbDisposable.dispose()
    }

}