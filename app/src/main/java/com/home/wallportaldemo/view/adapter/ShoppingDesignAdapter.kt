package com.home.wallportaldemo.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.home.wallportaldemo.R
import kotlinx.android.synthetic.main.fragment_shopping_design_recycler_view_item.view.*
import com.home.wallportaldemo.model.ShoppingDesignResponse
import com.home.wallportaldemo.view.adapter.ShoppingDesignAdapter.MyViewHolder

class ShoppingDesignAdapter(private val listener: OnItemClickListener) :
    PagedListAdapter<ShoppingDesignResponse, MyViewHolder>(diffCallback) {

    companion object {
        private val diffCallback =
            object : DiffUtil.ItemCallback<ShoppingDesignResponse>() {
                override fun areItemsTheSame(
                    oldItem: ShoppingDesignResponse, newItem: ShoppingDesignResponse
                ): Boolean = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: ShoppingDesignResponse, newItem: ShoppingDesignResponse
                ): Boolean = oldItem == newItem
            }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_shopping_design_recycler_view_item, parent, false)
        return MyViewHolder(imageView, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val response = getItem(position)
        response?.apply {
            holder.itemView.text_view_name.text = name
            holder.itemView.text_view_email.text = email
        }
    }

    class MyViewHolder(imageView: View, private var listener: OnItemClickListener) :
        RecyclerView.ViewHolder(imageView), View.OnClickListener {
        init {
            imageView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(adapterPosition)
        }
    }
}
