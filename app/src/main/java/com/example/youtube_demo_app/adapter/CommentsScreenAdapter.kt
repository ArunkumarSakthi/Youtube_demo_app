package com.example.youtube_demo_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube_demo_app.R
import com.example.youtube_demo_app.databinding.ItemCommentsBinding
import com.example.youtube_demo_app.model.UserCommentsResponse

class CommentsScreenAdapter(
    private val userCommentsResponse: List<UserCommentsResponse>,
) : RecyclerView.Adapter<CommentsScreenAdapter.CommentScreenViewHolder>() {

    override fun getItemCount() = userCommentsResponse.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CommentScreenViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_comments,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CommentScreenViewHolder, position: Int) {
        holder.itemCommentsBinding.commentsResponse = userCommentsResponse[position]
        holder.itemCommentsBinding.tvName.text= userCommentsResponse.get(position).name
        holder.itemCommentsBinding.tvEmail.text= userCommentsResponse.get(position).email
        holder.itemCommentsBinding.tvBody.text = userCommentsResponse.get(position).body

    }


    inner class CommentScreenViewHolder(
        val itemCommentsBinding: ItemCommentsBinding
    ) : RecyclerView.ViewHolder(itemCommentsBinding.root)

}