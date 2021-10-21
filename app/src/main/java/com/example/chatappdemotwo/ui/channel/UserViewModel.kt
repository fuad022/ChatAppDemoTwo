package com.example.chatappdemotwo.ui.channel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatappdemotwo.model.UserModel
import com.example.chatappdemotwo.repository.ChatRepository
import com.example.chatappdemotwo.util.UsersMockData

class UserViewModel(private val chatRepository: ChatRepository) : ViewModel() {
    private val _mockUserList = MutableLiveData<List<UserModel>>()
    val mockUserList: LiveData<List<UserModel>> get() = _mockUserList

    init {
        _mockUserList.value = chatRepository.getUsers()
    }
}
