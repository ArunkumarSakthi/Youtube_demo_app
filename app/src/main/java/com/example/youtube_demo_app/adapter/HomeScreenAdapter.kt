package com.example.youtube_demo_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube_demo_app.R
import com.example.youtube_demo_app.databinding.ItemVideoBinding
import com.example.youtube_demo_app.model.UserResponse
import com.example.youtube_demo_app.util.RecyclerViewClickListener

class HomeScreenAdapter(
    private val userResponse: List<UserResponse>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<HomeScreenAdapter.HomeScreenViewHolder>() {

    override fun getItemCount() = userResponse.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeScreenViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_video,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HomeScreenViewHolder, position: Int) {
        holder.itemVideoBinding.userResponse = userResponse[position]

        holder.itemVideoBinding.videoTitle.text= userResponse.get(position).name
        holder.itemVideoBinding.channelName.text = userResponse.get(position).username
        holder.itemVideoBinding.clMain.setOnClickListener {
            listener.onRecyclerViewItemClick(position,userResponse[position].id.toString())
        }

    }


    inner class HomeScreenViewHolder(
        val itemVideoBinding: ItemVideoBinding
    ) : RecyclerView.ViewHolder(itemVideoBinding.root)

}