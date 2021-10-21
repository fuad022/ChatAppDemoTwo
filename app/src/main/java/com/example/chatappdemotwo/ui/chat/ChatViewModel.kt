package com.example.chatappdemotwo.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatappdemotwo.model.ChatModel
import com.example.chatappdemotwo.repository.ChatRepository

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {
    private val _chatData = MutableLiveData<ChatModel>()
    val chatDataList: LiveData<ChatModel> get() = _chatData

    fun sendMessageData(chatModel: ChatModel) {
        _chatData.value = chatRepository.sendMessage(chatModel)
    }
}
