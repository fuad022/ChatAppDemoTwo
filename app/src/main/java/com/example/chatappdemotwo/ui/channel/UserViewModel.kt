package com.example.chatappdemotwo.ui.channel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatappdemotwo.model.UserModel
import com.example.chatappdemotwo.util.UsersMockData

class UserViewModel : ViewModel() {
    private val usersList = UsersMockData()
    private val _mockUserList = MutableLiveData<List<UserModel>>()
    val mockUserList: LiveData<List<UserModel>> get() = _mockUserList

    init {
        _mockUserList.value = usersList.usersData()
    }
}
