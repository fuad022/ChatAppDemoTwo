package com.example.chatappdemotwo.repository

import com.example.chatappdemotwo.model.ChatModel
import com.example.chatappdemotwo.model.UserModel
import com.example.chatappdemotwo.util.UsersMockData

class ChatRepositoryImpl(private val usersList: UsersMockData) : ChatRepository {
    override fun getUsers(): List<UserModel> {
        return usersList.usersData()
    }

    override fun sendMessage(chatModel: ChatModel): ChatModel {
        return chatModel
    }
}