package com.example.chatappdemotwo.ui.channel

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatappdemotwo.R
import com.example.chatappdemotwo.adapter.UserHorizontalAdapter
import com.example.chatappdemotwo.adapter.UserVerticalAdapter
import com.example.chatappdemotwo.databinding.FragmentChannelBinding
import com.example.chatappdemotwo.model.UserModel

class ChannelFragment : Fragment() {
    private val binding by lazy { FragmentChannelBinding.inflate(layoutInflater) }
    private val viewModel: UserViewModel by viewModels()
    private val userHorizontalAdapter = UserHorizontalAdapter()
    private val userVerticalAdapter = UserVerticalAdapter()
    private val usersList = arrayListOf<UserModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initToolbar()
        doSearch()
        setupHorizontalAdapter()
        setupVerticalAdapter()
        return binding.root
    }

    private fun doSearch() {
        binding.search.doAfterTextChanged {
            filter(it.toString())
        }
    }

    private fun filter(text: String) {
        val filteredUsersList = arrayListOf<UserModel>()
        usersList.forEach {
            if (text.lowercase() in it.friendUsername.toString().lowercase())
                filteredUsersList.add(it)
            userVerticalAdapter.submitList(filteredUsersList.toMutableList())
        }
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
            usersList.addAll(it)
            userVerticalAdapter.submitList(it.toMutableList())
            binding.usersRecyclerViewVertical.layoutManager = LinearLayoutManager(activity)
            binding.usersRecyclerViewVertical.adapter = userVerticalAdapter
        })
    }
}










