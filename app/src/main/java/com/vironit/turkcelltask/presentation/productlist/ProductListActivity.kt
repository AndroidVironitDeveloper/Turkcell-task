package com.vironit.turkcelltask.presentation.productlist

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vironit.turkcelltask.Constants.APP_LOG
import com.vironit.turkcelltask.Constants.PRODUCT_ID_KEY
import com.vironit.turkcelltask.R
import com.vironit.turkcelltask.base.BaseActivity
import com.vironit.turkcelltask.presentation.productdetails.ProductDetailsActivity

class ProductListActivity : BaseActivity<ProductListViewModel>() {

    private lateinit var productListRecycler: RecyclerView
    private lateinit var adapter: ProductListAdapter

    override fun getViewModelClass(): Class<ProductListViewModel> = ProductListViewModel::class.java

    override fun layoutId(): Int = R.layout.activity_product_list

    override fun initViews() {
        productListRecycler = findViewById(R.id.rv_a_product_list)
        productListRecycler.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        adapter = ProductListAdapter { id -> onItemClicked(id) }
        productListRecycler.adapter = adapter
        productListRecycler.addItemDecoration(ItemOffsetDecoration(16))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.productListLiveData.observe(this, Observer { adapter.setProductList(it) })
        viewModel.getProductList()
    }

    override fun onStart() {
        super.onStart()
        viewModel.updateProductList()
    }

    override fun onStop() {
        super.onStop()
        viewModel.releaseOnStopResources()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.releaseOnDestroyResources()
    }

    private fun onItemClicked(id: String) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
            .apply { putExtra(PRODUCT_ID_KEY, id) }
        startActivity(intent)
    }
}