package com.vironit.turkcelltask.presentation.productlist

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vironit.turkcelltask.R
import com.vironit.turkcelltask.base.BaseViewHolder


class ProductListAdapter(  private val clickListener: (String) -> Unit) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private var productList: List<ProductPresentModel> = emptyList()

    fun setProductList(products: List<ProductPresentModel>) {
        productList = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(productList[position], position)
    }


    inner class ProductListViewHolder(itemView: View) :
        BaseViewHolder<ProductPresentModel>(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.iv_item_product_image)
        private val nameView: TextView = itemView.findViewById(R.id.tv_item_product_name)
        private val priceView: TextView = itemView.findViewById(R.id.tv_item_product_price)

        override fun bind(dataItem: ProductPresentModel, position: Int) {
            Glide.with(itemView)
                .load(dataItem.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)

            nameView.text = dataItem.name
            priceView.text = dataItem.price.toString()

            itemView.setOnClickListener{
                clickListener.invoke(dataItem.id)
            }
        }

    }
}

class ItemOffsetDecoration(private val mItemOffset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
    }
}
