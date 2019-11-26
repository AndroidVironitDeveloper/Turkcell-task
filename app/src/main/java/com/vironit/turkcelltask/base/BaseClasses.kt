package com.vironit.turkcelltask.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

abstract class BaseActivity<VM : AndroidViewModel> : AppCompatActivity(){

    protected lateinit var viewModel: VM
    protected abstract fun getViewModelClass(): Class<VM>
    @LayoutRes
    protected abstract fun layoutId(): Int

    protected abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initViews()
        viewModel = ViewModelProviders.of(this).get(getViewModelClass())
    }
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(dataItem: T, position: Int)
}