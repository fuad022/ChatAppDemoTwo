package com.example.chatappdemotwo.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatappdemotwo.model.ChatModel

class ChatViewModel : ViewModel() {
    private val _chatData = MutableLiveData<ChatModel>()
    val chatDataList: LiveData<ChatModel> get() = _chatData

    fun sendMessageData(chatModel: ChatModel) {
        _chatData.value = chatModel
    }
}