package com.example.chatappdemotwo.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappdemotwo.R
import com.example.chatappdemotwo.databinding.FriendMessageItemBinding
import com.example.chatappdemotwo.model.ChatModel

class ChatFriendMessageItemHolder(private val binding: FriendMessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(chat: ChatModel) {
        binding.friendChatImage.setImageResource(chat.image)
        binding.friendMessageText.text = chat.message
        if (chat.isFriendOnline)
            binding.indicator.setBackgroundResource(R.drawable.online)
        else
            binding.indicator.setBackgroundResource(R.drawable.offline)
    }

    companion object {
        fun from(parent: ViewGroup): ChatFriendMessageItemHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FriendMessageItemBinding.inflate(layoutInflater, parent, false)
            return ChatFriendMessageItemHolder(binding)
        }
    }
}
