package com.vironit.turkcelltask.presentation.productdetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vironit.turkcelltask.App
import com.vironit.turkcelltask.Constants.APP_LOG
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ProductDetailsViewModel(app: Application) : AndroidViewModel(app) {

    val productDetailsLiveData = MutableLiveData<ProductDetailsPresentModel>()

    private lateinit var apiDisposable: Disposable

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var productDetailsInteractor: ProductDetailsContact.Interactor

    fun getProductDetails(id: String?) {
        id?.let {
            apiDisposable = productDetailsInteractor.loadProductDetails(it)
                .subscribe(
                    { product -> productDetailsLiveData.postValue(product) },
                    { error -> Log.e(APP_LOG, error.localizedMessage ?: "") })
        }
    }


    fun releaseOnStopResources() {
        if (!apiDisposable.isDisposed && ::apiDisposable.isInitialized) apiDisposable.dispose()
    }

}