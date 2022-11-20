package com.example.youtube_demo_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube_demo_app.adapter.PostScreenAdapter
import com.example.youtube_demo_app.databinding.FragmentPostBinding
import com.example.youtube_demo_app.factory.PostViewModelFactory
import com.example.youtube_demo_app.network.RetroInstance
import com.example.youtube_demo_app.repository.PostRepository
import com.example.youtube_demo_app.util.Connector
import com.example.youtube_demo_app.util.NetworkConnectionInterceptor
import com.example.youtube_demo_app.util.RecyclerViewClickListener
import com.example.youtube_demo_app.viewmodel.PostViewModel

class PostFragment : Fragment() ,RecyclerViewClickListener{

    private lateinit var connector: Connector

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    var receiveData: String? = ""

    private lateinit var factory: PostViewModelFactory
    private lateinit var postViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        receiveData = arguments?.getString("postid")
        connector = activity as Connector
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        val api = RetroInstance(networkConnectionInterceptor)
        val stringToLong = receiveData!!.toLong()
        val repository = PostRepository(api,stringToLong)

        factory = PostViewModelFactory(repository)
        postViewModel = ViewModelProvider(this, factory).get(PostViewModel::class.java)

        postViewModel.getPostApiData()


        postViewModel.userPostData.observe(viewLifecycleOwner, Observer {
            postResponse ->binding.recyclerViewPost.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
            it.adapter = PostScreenAdapter(postResponse, this)
        }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecyclerViewItemClick(position: Int, userResponse: String?) {
        connector.sendData(userResponse)
    }
}