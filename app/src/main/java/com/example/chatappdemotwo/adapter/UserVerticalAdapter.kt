package com.example.chatappdemotwo.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappdemotwo.adapter.holder.UserGroupVerticalItemHolder
import com.example.chatappdemotwo.adapter.holder.UserVerticalItemHolder
import com.example.chatappdemotwo.model.UserModel

class UserVerticalAdapter(private val group: Boolean = false) : ListAdapter<UserModel, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (group) UserGroupVerticalItemHolder.from(parent)
        else UserVerticalItemHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserGroupVerticalItemHolder -> holder.bind(getItem(position), holder)
            is UserVerticalItemHolder -> holder.bind(getItem(position), holder)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel) =
            oldItem.friendImage == newItem.friendImage

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel) =
            oldItem == newItem
    }

    override fun submitList(list: MutableList<UserModel>?) {
        super.submitList(list?.map { it.copy() })
    }
}