package com.example.chatappdemotwo.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappdemotwo.databinding.MyMessageItemBinding
import com.example.chatappdemotwo.model.ChatModel

class ChatMyMessageItemHolder(private val binding: MyMessageItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(chat: ChatModel) {
        binding.myChatImage.setImageResource(chat.image)
        binding.myMessageText.text = chat.message
    }

    companion object {
        fun from(parent: ViewGroup): ChatMyMessageItemHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MyMessageItemBinding.inflate(layoutInflater, parent, false)
            return ChatMyMessageItemHolder(binding)
        }
    }
}