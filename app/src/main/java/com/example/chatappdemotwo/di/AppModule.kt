package com.example.chatappdemotwo.di

import com.example.chatappdemotwo.repository.ChatRepository
import com.example.chatappdemotwo.repository.ChatRepositoryImpl
import com.example.chatappdemotwo.ui.channel.UserViewModel
import com.example.chatappdemotwo.ui.chat.ChatViewModel
import com.example.chatappdemotwo.util.UsersMockData
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { UsersMockData() }
    single<ChatRepository> { ChatRepositoryImpl(get()) }

    viewModel { UserViewModel(get()) }
    viewModel { ChatViewModel(get()) }
}
