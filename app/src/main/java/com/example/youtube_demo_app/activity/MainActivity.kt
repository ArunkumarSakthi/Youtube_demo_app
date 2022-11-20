package com.example.youtube_demo_app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.youtube_demo_app.R
import com.example.youtube_demo_app.databinding.ActivityMainBinding
import com.example.youtube_demo_app.fragment.CommentsFragment
import com.example.youtube_demo_app.fragment.HomeScreenFragment
import com.example.youtube_demo_app.fragment.PostFragment
import com.example.youtube_demo_app.fragment.UserDetailFragment
import com.example.youtube_demo_app.util.Communicator
import com.example.youtube_demo_app.util.Connector
import com.example.youtube_demo_app.util.TransferData


class MainActivity : AppCompatActivity(),Communicator,TransferData, Connector {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the home fragment
        loadFragment()

    }

    private fun loadFragment() {
        val homeScreenFragment = HomeScreenFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout,homeScreenFragment)
            .commit()
    }


    override fun passData(data: String?) {
        val bundle= Bundle()
        bundle.putString("passingData",data)
        val transaction = this.supportFragmentManager.beginTransaction()
        val userDetailFragment = UserDetailFragment()
        userDetailFragment.arguments = bundle
        transaction.replace(R.id.frame_layout,userDetailFragment)
            .addToBackStack(null)
            .commit()

    }

    override fun dataTransfer(data: String?) {
        val bundle= Bundle()
        bundle.putString("postid",data)
        val transaction = this.supportFragmentManager.beginTransaction()
        val postFragment = PostFragment()
        postFragment.arguments = bundle
        transaction.replace(R.id.frame_layout,postFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun sendData(data: String?) {
        val bundle= Bundle()
        bundle.putString("commentsid",data)
        val transaction = this.supportFragmentManager.beginTransaction()
        val commentsFragment = CommentsFragment()
        commentsFragment.arguments = bundle
        transaction.replace(R.id.frame_layout,commentsFragment)
            .addToBackStack(null)
            .commit()
    }
}