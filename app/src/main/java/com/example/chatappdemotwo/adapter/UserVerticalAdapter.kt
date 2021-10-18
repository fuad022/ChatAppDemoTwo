package com.example.chatappdemotwo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappdemotwo.R
import com.example.chatappdemotwo.databinding.UserChannelVerticalLayoutBinding
import com.example.chatappdemotwo.databinding.UsersGroupChannelVerticalLayoutBinding
import com.example.chatappdemotwo.model.UserModel

class UserVerticalAdapter : ListAdapter<UserModel, RecyclerView.ViewHolder>(DiffCallback()) {
    private val itemGroup = 0
    private val itemSingle = 1

    inner class UserVerticalItemHolder(private val binding: UserChannelVerticalLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel: UserModel?) {
            userModel?.let { model ->
                binding.imgFriendVertical.setImageResource(model.friendImage!!)
                binding.usernameFriendVertical.text = model.friendUsername
                binding.lastActiveTimeVertical.text = model.activeTime
                if (model.isFriendOnline!!)
                    binding.indicator.setBackgroundResource(R.drawable.online)
                else
                    binding.indicator.setBackgroundResource(R.drawable.offline)
                binding.root.setOnClickListener { model.let { user -> setOnItemClick?.invoke(user) } }
            }
        }
    }

    inner class UserGroupVerticalItemHolder(private val binding: UsersGroupChannelVerticalLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel: UserModel?) {
            userModel?.let { model ->
                binding.imgMyUserGroupVertical.setImageResource(model.myImage!!)
                binding.imgFriendUserGroupVertical.setImageResource(model.friendImage!!)
                binding.myNameGroupVertical.text = model.myUsername
                binding.friendNameGroupVertical.text = model.friendUsername
                binding.lastActiveTimeGroupVertical.text = model.activeTime
                binding.root.setOnClickListener { model.let { user -> setOnItemClick?.invoke(user) } }
            }
        }
    }

    fun fromVerticalItemHolder(parent: ViewGroup): UserVerticalItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserChannelVerticalLayoutBinding.inflate(layoutInflater, parent, false)
        return UserVerticalItemHolder(binding)
    }

    fun fromGroupVerticalItemHolder(parent: ViewGroup): UserGroupVerticalItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UsersGroupChannelVerticalLayoutBinding.inflate(layoutInflater, parent, false)
        return UserGroupVerticalItemHolder(binding)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == itemGroup)
            fromGroupVerticalItemHolder(parent)
        else
            fromVerticalItemHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserGroupVerticalItemHolder -> holder.bind(getItem(position))
            is UserVerticalItemHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int) =
        if (getItem(position).isGroup!!)
            itemGroup
        else
            itemSingle

    private class DiffCallback : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel) =
            oldItem == newItem
    }

    override fun submitList(list: List<UserModel>?) {
        super.submitList(list?.map { it.copy() })
    }

    private var setOnItemClick: ((UserModel) -> Unit)? = null

    fun setOnClickListener(listener: (UserModel) -> Unit) { setOnItemClick = listener }
}