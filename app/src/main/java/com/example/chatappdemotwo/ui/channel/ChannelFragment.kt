package com.example.chatappdemotwo.ui.channel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatappdemotwo.R
import com.example.chatappdemotwo.adapter.UserHorizontalAdapter
import com.example.chatappdemotwo.adapter.UserVerticalAdapter
import com.example.chatappdemotwo.databinding.FragmentChannelBinding
import com.example.chatappdemotwo.model.UserModel
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
    private var userModelList = ArrayList<UserModel>()
    private var finalUserModelList = ArrayList<UserModel>()
    private var userHorizontalAdapter = UserHorizontalAdapter()
    private var userVerticalAdapter = UserVerticalAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init()
        return binding.root
    }

    private fun init() {
        binding.apply {
            setHasOptionsMenu(true)
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbarChannel)

            setupHorizontalChannels()
            setupVerticalChannels()
        }
    }

    private fun activeTime(): String {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return "$hour:$minute"
    }

    private fun setupUsers(): ArrayList<UserModel> {
        userModelList.add(UserModel("Frank","Marta",imageList[0],imageList[1],activeTime(),false))
        userModelList.add(UserModel("Frank","Francis",imageList[0],imageList[3],activeTime(),true))
        userModelList.add(UserModel("Frank","Gonzales",imageList[0],imageList[5],activeTime(),false))
        userModelList.add(UserModel("Frank","Martina",imageList[0],imageList[2],activeTime(),true))
        Log.d("UserModelList", "UserModelList - " + userModelList)
        return userModelList
    }

    //// Horizontal Users //////
    private fun setupHorizontalChannels() {
        setupHorizontalRecyclerView()
    }

    private fun setupHorizontalRecyclerView() {
        binding.apply {
            usersRecyclerViewHorizontal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            usersRecyclerViewHorizontal.adapter = loadHorizontalUser()
        }
    }

    private fun loadHorizontalUser(): UserHorizontalAdapter {
        setupUsers().forEachIndexed { _, userModel ->
            if (!userModel.isGroup) {
                finalUserModelList.add(userModel)
            }
        }
        userHorizontalAdapter.submitList(finalUserModelList)
        return userHorizontalAdapter
    }
    //// Horizontal Users //////

    //// Vertical Users /////
    private fun setupVerticalChannels() {
        setupVerticalRecyclerView()
    }

    private fun setupVerticalRecyclerView() {
        binding.apply {
            usersRecyclerViewHorizontal.layoutManager = LinearLayoutManager(context)
            usersRecyclerViewHorizontal.adapter = loadVerticalUser()
        }
    }

    private fun loadVerticalUser(): UserVerticalAdapter {
        setupUsers().forEachIndexed { _, userModel ->
            if (!userModel.isGroup) {
                finalUserModelList.add(userModel)
            } else {
                userVerticalAdapter = UserVerticalAdapter(true)
                finalUserModelList.add(userModel)
            }
        }

        userVerticalAdapter.submitList(finalUserModelList)
        return userVerticalAdapter
    }
    //// Vertical Users /////
}










