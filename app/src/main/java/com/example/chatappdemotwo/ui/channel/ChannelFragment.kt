package com.example.chatappdemotwo.ui.channel

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.chatappdemotwo.R
import com.example.chatappdemotwo.adapter.UserHorizontalAdapter
import com.example.chatappdemotwo.adapter.UserVerticalAdapter
import com.example.chatappdemotwo.databinding.FragmentChannelBinding
import com.example.chatappdemotwo.model.UserModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChannelFragment : Fragment() {
    private val binding by lazy { FragmentChannelBinding.inflate(layoutInflater) }
    private val viewModel: UserViewModel by viewModel()
    private val userHorizontalAdapter = UserHorizontalAdapter()
    private val userVerticalAdapter = UserVerticalAdapter()
    private val usersList = arrayListOf<UserModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initToolbar()
        binding.search.setupClearButtonWithAction()
        setupHorizontalAdapter()
        setupVerticalAdapter()
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    fun EditText.setupClearButtonWithAction() {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(ed: Editable?) {
                filter(ed.toString())
                val clearIcon = if (ed?.isNotEmpty() == true) R.drawable.ic_clear else 0
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search, 0, clearIcon, 0)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        })

        setOnTouchListener(View.OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (this.right - this.compoundPaddingRight)) {
                    this.setText("")
                    return@OnTouchListener true
                }
            }
            return@OnTouchListener false
        })
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
            binding.usersRecyclerViewVertical.adapter = userVerticalAdapter
        })
    }
}
