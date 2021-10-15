package com.example.chatappdemotwo.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappdemotwo.adapter.holder.ChatFriendMessageItemHolder
import com.example.chatappdemotwo.adapter.holder.ChatMyMessageItemHolder
import com.example.chatappdemotwo.model.ChatModel

class ChatAdapter : ListAdapter<ChatModel, RecyclerView.ViewHolder>(DiffCallback()) {

    private val itemTypeMe = 0
    private val itemTypeSender = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == itemTypeMe)
            ChatMyMessageItemHolder.from(parent)
        else
            ChatFriendMessageItemHolder.from(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ChatMyMessageItemHolder -> holder.bind(getItem(position))
            is ChatFriendMessageItemHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int) =
        if (getItem(position).isMe)
            itemTypeMe
        else
            itemTypeSender

    private class DiffCallback : DiffUtil.ItemCallback<ChatModel>() {
        override fun areItemsTheSame(oldItem: ChatModel, newItem: ChatModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ChatModel, newItem: ChatModel) =
            oldItem == newItem
    }

    override fun submitList(list: List<ChatModel>?) {
        super.submitList(list?.map { it.copy() })
    }
}