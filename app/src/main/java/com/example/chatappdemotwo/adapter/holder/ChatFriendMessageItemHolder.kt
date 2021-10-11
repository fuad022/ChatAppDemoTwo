package com.example.chatappdemotwo.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappdemotwo.databinding.FriendMessageItemBinding
import com.example.chatappdemotwo.model.ChatModel

class ChatFriendMessageItemHolder(private val binding: FriendMessageItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(chat: ChatModel) {
        binding.apply {
            friendChatImage.setImageResource(chat.image)
            friendMessageText.text = chat.message
        }
    }

    companion object {
        fun from(parent: ViewGroup): ChatFriendMessageItemHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FriendMessageItemBinding.inflate(layoutInflater, parent, false)
            return ChatFriendMessageItemHolder(binding)
        }
    }
}
