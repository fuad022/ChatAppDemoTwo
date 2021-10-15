package com.example.chatappdemotwo.ui.channel

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatappdemotwo.R
import com.example.chatappdemotwo.adapter.UserHorizontalAdapter
import com.example.chatappdemotwo.adapter.UserVerticalAdapter
import com.example.chatappdemotwo.databinding.FragmentChannelBinding
import com.example.chatappdemotwo.model.UserModel
import java.lang.Exception
import java.nio.channels.Selector

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChannelFragment : Fragment() {
    private val binding by lazy { FragmentChannelBinding.inflate(layoutInflater) }
    private val imageList = listOf(
        R.drawable.photo_1,
        R.drawable.photo_2,
        R.drawable.photo_3,
        R.drawable.photo_4,
        R.drawable.photo_5,
        R.drawable.photo_6,
        R.drawable.photo_7
    )
    private var verticalUsersList = ArrayList<UserModel>()

    private val viewModel: UserViewModel by viewModels()
    private val userHorizontalAdapter = UserHorizontalAdapter()
    private val userVerticalAdapter = UserVerticalAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initToolbar()
        setupHorizontalAdapter()
        setupVerticalAdapter()

        return binding.root
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarChannel)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    private fun setupHorizontalAdapter() {
        userHorizontalAdapter.setOnClickListener { user ->
            user.let {
                findNavController().navigate(
                    ChannelFragmentDirections.actionChannelFragmentToChatFragment(user)
                )
            }
        }
        observeHorizontalList()
    }

    private fun observeHorizontalList() {
        viewModel.mockUserList.observe(viewLifecycleOwner, {
            userHorizontalAdapter.submitList(it.filter { !it.isGroup!! }.toMutableList())
            binding.usersRecyclerViewHorizontal.adapter = userHorizontalAdapter
        })
    }

    private fun setupVerticalAdapter() {
        userVerticalAdapter.setOnClickListener { user ->
            user.let {
                findNavController().navigate(
                    ChannelFragmentDirections.actionChannelFragmentToChatFragment(user)
                )
            }
        }
        observeVerticalList()
    }

    private fun observeVerticalList() {
        viewModel.mockUserList.observe(viewLifecycleOwner, {
            userVerticalAdapter.submitList(it.toMutableList())
            binding.usersRecyclerViewVertical.layoutManager = LinearLayoutManager(activity)
            binding.usersRecyclerViewVertical.adapter = userVerticalAdapter
        })
    }
}










