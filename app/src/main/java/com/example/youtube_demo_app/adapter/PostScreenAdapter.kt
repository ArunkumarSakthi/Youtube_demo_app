package com.example.youtube_demo_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube_demo_app.R
import com.example.youtube_demo_app.databinding.ItemPostBinding
import com.example.youtube_demo_app.model.UserPostResponse
import com.example.youtube_demo_app.util.RecyclerViewClickListener

class PostScreenAdapter (
    private val userPostResponse: List<UserPostResponse>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<PostScreenAdapter.PostScreenViewHolder>() {

    override fun getItemCount() = userPostResponse.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostScreenViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_post,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PostScreenViewHolder, position: Int) {
        holder.itemPostBinding.postResponse = userPostResponse[position]

        holder.itemPostBinding.tvTitle.text= userPostResponse.get(position).title
        holder.itemPostBinding.tvBody.text = userPostResponse.get(position).body
        holder.itemPostBinding.clMain.setOnClickListener {
            listener.onRecyclerViewItemClick(position,userPostResponse[position].id.toString())
        }

    }


    inner class PostScreenViewHolder(
        val itemPostBinding: ItemPostBinding
    ) : RecyclerView.ViewHolder(itemPostBinding.root)

}