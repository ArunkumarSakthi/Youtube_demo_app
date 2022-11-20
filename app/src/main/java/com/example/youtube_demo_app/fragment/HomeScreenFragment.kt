package com.example.youtube_demo_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube_demo_app.adapter.HomeScreenAdapter
import com.example.youtube_demo_app.databinding.FragmentHomeScreenBinding
import com.example.youtube_demo_app.factory.UserViewModelFactory
import com.example.youtube_demo_app.network.RetroInstance
import com.example.youtube_demo_app.repository.HomeScreenRepository
import com.example.youtube_demo_app.util.Communicator
import com.example.youtube_demo_app.util.NetworkConnectionInterceptor
import com.example.youtube_demo_app.util.RecyclerViewClickListener
import com.example.youtube_demo_app.viewmodel.HomeScreenViewModel

class HomeScreenFragment : Fragment(), RecyclerViewClickListener {

    private lateinit var communicator: Communicator

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: UserViewModelFactory
    private lateinit var viewModel: HomeScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        communicator = activity as Communicator
        return binding.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        val api = RetroInstance(networkConnectionInterceptor)
        val repository = HomeScreenRepository(api)

        factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HomeScreenViewModel::class.java)

        viewModel.getApiData()

        viewModel.homescreenData.observe(viewLifecycleOwner, Observer { userResponse ->
            binding.recyclerViewUser.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = HomeScreenAdapter(userResponse, this)

            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecyclerViewItemClick(position: Int, userResponse: String?) {
        communicator.passData(userResponse)
    }

}