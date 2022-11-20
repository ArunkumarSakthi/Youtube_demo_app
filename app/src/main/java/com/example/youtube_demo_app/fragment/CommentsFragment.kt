package com.example.youtube_demo_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube_demo_app.adapter.CommentsScreenAdapter
import com.example.youtube_demo_app.databinding.FragmentCommentsBinding
import com.example.youtube_demo_app.factory.CommentsViewModelFactory
import com.example.youtube_demo_app.network.RetroInstance
import com.example.youtube_demo_app.repository.CommentsRepository
import com.example.youtube_demo_app.util.NetworkConnectionInterceptor
import com.example.youtube_demo_app.viewmodel.CommentsViewModel

class CommentsFragment : Fragment(){


    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!
    var receiveData: String? = ""

    private lateinit var factory: CommentsViewModelFactory
    private lateinit var commentsViewModel: CommentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        receiveData = arguments?.getString("commentsid")
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        val api = RetroInstance(networkConnectionInterceptor)
        val stringToLong = receiveData!!.toLong()
        val repository = CommentsRepository(api,stringToLong)

        factory = CommentsViewModelFactory(repository)
        commentsViewModel = ViewModelProvider(this, factory).get(CommentsViewModel::class.java)

        commentsViewModel.getCommentsApiData()


        commentsViewModel.userCommentsData.observe(viewLifecycleOwner, Observer {
            commentsResponse -> binding.recyclerViewComments.also{
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
            it.adapter = CommentsScreenAdapter(commentsResponse)
        }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}