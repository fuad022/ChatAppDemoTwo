package com.example.chatappdemotwo.repository

import com.example.chatappdemotwo.model.ChatModel
import com.example.chatappdemotwo.model.UserModel

interface ChatRepository {
    fun getUsers(): List<UserModel>
    fun sendMessage(chatModel: ChatModel): ChatModel
}