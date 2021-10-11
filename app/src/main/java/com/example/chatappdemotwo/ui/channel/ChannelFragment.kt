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
    private var horizontalUsersList = ArrayList<UserModel>()
    private var verticalUsersList = ArrayList<UserModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        init()
        setupHorizontalAdapter()
        setupVerticalAdapter()
        return binding.root
    }

    private fun init() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarChannel)
    }

    private fun activeTime(): String {
//        val calendar = Calendar.getInstance()
//        val time = "${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}"
//        Log.d("Time", "Time - " + time)
        val c = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val time = simpleDateFormat.format(c.time).toString()
        return time
    }

    private fun horizontalUsersList(): ArrayList<UserModel> {
        horizontalUsersList.add(UserModel("Frank","Marta",imageList[0],imageList[1],activeTime(),false))
        horizontalUsersList.add(UserModel("Frank","Francis",imageList[0],imageList[3],activeTime(),true))
        horizontalUsersList.add(UserModel("Frank","Gonzales",imageList[0],imageList[5],activeTime(),false))
        horizontalUsersList.add(UserModel("Frank","Martina",imageList[0],imageList[2],activeTime(),true))
        Log.d("horizontalUsersList", "horizontalUsersList - " + horizontalUsersList)
        return horizontalUsersList
    }

    private fun verticalUsersList(): ArrayList<UserModel> {
        verticalUsersList.add(UserModel("Frank","Marta",imageList[0],imageList[1],activeTime(),false))
        verticalUsersList.add(UserModel("Frank","Francis",imageList[0],imageList[3],activeTime(),true))
        verticalUsersList.add(UserModel("Frank","Gonzales",imageList[0],imageList[5],activeTime(),false))
        verticalUsersList.add(UserModel("Frank","Martina",imageList[0],imageList[2],activeTime(),true))
        Log.d("verticalUsersList", "verticalUsersList - " + verticalUsersList)
        return verticalUsersList
    }

    private fun setupHorizontalAdapter() {
        UserHorizontalAdapter().apply {
            submitList(horizontalUsersList().filter { !it.isGroup }.toMutableList())
            binding.usersRecyclerViewHorizontal.adapter = this
        }
    }

    private fun setupVerticalAdapter() {
        UserVerticalAdapter().apply {
            submitList(verticalUsersList().toMutableList())
            binding.usersRecyclerViewVertical.layoutManager = LinearLayoutManager(activity)
            binding.usersRecyclerViewVertical.adapter = this
        }
    }

}










