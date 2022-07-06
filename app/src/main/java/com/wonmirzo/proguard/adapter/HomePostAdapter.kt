package com.wonmirzo.proguard.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wonmirzo.proguard.R
import com.wonmirzo.proguard.databinding.ItemHomeViewBinding
import com.wonmirzo.proguard.listener.OnBottomReachedListener
import com.wonmirzo.proguard.network.model.HomePost

class HomePostAdapter(
    var posts: ArrayList<HomePost>,
    var listener: OnBottomReachedListener
) :
    RecyclerView.Adapter<HomePostAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePostAdapter.VH {
        return VH(
            ItemHomeViewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: HomePostAdapter.VH, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = posts.size

    inner class VH(private var binding: ItemHomeViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val post = posts[position]
            binding.apply {
                Glide.with(ivPhoto.context)
                    .load(post.urls.small)
                    .placeholder(ColorDrawable(Color.parseColor(post.color)))
                    .fitCenter()
                    .into(ivPhoto)
            }

            if (position == posts.size - 1) {
                listener.onBottomReached(position)
            }
        }
    }
}