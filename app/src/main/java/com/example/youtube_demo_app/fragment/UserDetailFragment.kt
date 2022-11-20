package com.example.youtube_demo_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_demo_app.databinding.FragmentUserDetailBinding
import com.example.youtube_demo_app.factory.UserDetailViewModelFactory
import com.example.youtube_demo_app.network.RetroInstance
import com.example.youtube_demo_app.repository.UserDetailRepository
import com.example.youtube_demo_app.util.NetworkConnectionInterceptor
import com.example.youtube_demo_app.util.TransferData
import com.example.youtube_demo_app.viewmodel.UserDetailViewModel

class UserDetailFragment : Fragment() {

    private lateinit var transferData: TransferData

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!
    var receiveData: String? = ""

    private lateinit var userDetailViewModelFactory: UserDetailViewModelFactory
    private lateinit var userDetailViewModel: UserDetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        receiveData = arguments?.getString("passingData")
        transferData = activity as TransferData
        return binding.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        val api = RetroInstance(networkConnectionInterceptor)
        val stringToLong = receiveData!!.toLong()
        val userDetailRepository = UserDetailRepository(api,stringToLong)

        userDetailViewModelFactory = UserDetailViewModelFactory(userDetailRepository)

        userDetailViewModel = ViewModelProvider(this, userDetailViewModelFactory).get(UserDetailViewModel::class.java)

        userDetailViewModel.getUserDetailApiData()

        userDetailViewModel.observeMovieLiveData().observe(viewLifecycleOwner, Observer {
            result->
            binding.channelName.text = result.username
            binding.videoTitle.text = result.name
            binding.tvEmail.text = result.email
            binding.phoneno.text = result.phone

            binding.imageView10.setOnClickListener {
                transferData.dataTransfer(result.id.toString())
            }

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}