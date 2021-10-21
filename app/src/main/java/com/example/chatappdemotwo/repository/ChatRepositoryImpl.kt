package com.example.chatappdemotwo.repository

import com.example.chatappdemotwo.model.ChatModel
import com.example.chatappdemotwo.model.UserModel
import com.example.chatappdemotwo.util.UsersMockData

class ChatRepositoryImpl : ChatRepository {
    override fun getUsers(): List<UserModel> {
        val usersList = UsersMockData()
        return usersList.usersData()
    }

    override fun sendMessage(chatModel: ChatModel): ChatModel {
        return chatModel
    }
}