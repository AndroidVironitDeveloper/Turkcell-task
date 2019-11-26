package com.vironit.turkcelltask.presentation.productdetails

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vironit.turkcelltask.Constants.PRODUCT_ID_KEY
import com.vironit.turkcelltask.R
import com.vironit.turkcelltask.base.BaseActivity

class ProductDetailsActivity : BaseActivity<ProductDetailsViewModel>() {

    private lateinit var imageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun getViewModelClass(): Class<ProductDetailsViewModel> =
        ProductDetailsViewModel::class.java

    override fun layoutId(): Int = R.layout.activity_product_details

    override fun initViews() {
        imageView = findViewById(R.id.iv_a_product_details_image)
        nameTextView = findViewById(R.id.tv_a_product_details_name)
        priceTextView = findViewById(R.id.tv_a_product_details_price)
        descriptionTextView = findViewById(R.id.tv_a_product_details_description)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.productDetailsLiveData.observe(this, Observer {
            Glide.with(this)
                .load(it.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)

            nameTextView.text = it.name
            priceTextView.text = it.price.toString()
            descriptionTextView.text = it.description
        })
        val id = intent.extras?.getString(PRODUCT_ID_KEY)
        viewModel.getProductDetails(id)
    }

    override fun onStop() {
        super.onStop()
        viewModel.releaseOnStopResources()
    }

}