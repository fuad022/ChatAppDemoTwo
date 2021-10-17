package com.example.chatappdemotwo.ui.channel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatappdemotwo.R
import com.example.chatappdemotwo.model.UserModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class UserViewModel : ViewModel() {
    val _mockUserList: MutableLiveData<List<UserModel>> = MutableLiveData()
    val mockUserList: LiveData<List<UserModel>> get() = _mockUserList
    private val imageList = listOf(
        R.drawable.photo_1,
        R.drawable.photo_2,
        R.drawable.photo_3,
        R.drawable.photo_4,
        R.drawable.photo_5,
        R.drawable.photo_6,
        R.drawable.photo_7
    )

    private val userList = listOf(
        UserModel("Frank", "Marta", imageList[0], imageList[1], activeTime(), false, true),
        UserModel("Frank", "Francis", imageList[0], imageList[3], activeTime(), true, true),
        UserModel("Frank", "Gonzales", imageList[0], imageList[5], activeTime(), false, false),
        UserModel("Frank", "Martina", imageList[0], imageList[2], activeTime(), true, false)
    )

    private fun activeTime(): String {
        val calendar = Calendar.getInstance()
        val time = "${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}"
        Log.d("Time", "Time - " + time)
//        val c = Calendar.getInstance()
//        val simpleDateFormat = SimpleDateFormat("HH:mm")
//        val time = simpleDateFormat.format(c.time).toString()
        return time
    }

    init {
        _mockUserList.value = userList
    }


}














