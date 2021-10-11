package com.example.chatappdemotwo.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappdemotwo.adapter.holder.UserGroupVerticalItemHolder
import com.example.chatappdemotwo.adapter.holder.UserVerticalItemHolder
import com.example.chatappdemotwo.model.UserModel

class UserVerticalAdapter : ListAdapter<UserModel, RecyclerView.ViewHolder>(DiffCallback()) {

    private val itemGroup = 0
    private val itemSingle = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == itemGroup)
            UserGroupVerticalItemHolder.from(parent)
        else
            UserVerticalItemHolder.from(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserGroupVerticalItemHolder -> holder.bind(getItem(position), holder)
            is UserVerticalItemHolder -> holder.bind(getItem(position), holder)
        }
    }

    override fun getItemViewType(position: Int) =
        if (getItem(position).isGroup)
            itemGroup
        else
            itemSingle

    private class DiffCallback : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel) =
            oldItem.friendImage == newItem.friendImage

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel) =
            oldItem == newItem
    }

    override fun submitList(list: List<UserModel>?) {
        super.submitList(list?.map { it.copy() })
    }
}