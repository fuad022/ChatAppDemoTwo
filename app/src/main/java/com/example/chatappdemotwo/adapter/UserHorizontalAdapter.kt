package com.example.chatappdemotwo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappdemotwo.databinding.UserChannelHorizontalLayoutBinding
import com.example.chatappdemotwo.model.UserModel
import com.example.chatappdemotwo.ui.channel.ChannelFragmentDirections

class UserHorizontalAdapter : ListAdapter<UserModel, UserHorizontalAdapter.ItemHolder>(DiffCallback()) {

    class ItemHolder(private val binding: UserChannelHorizontalLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserModel, holder: ItemHolder) {
            binding.apply {
                imgUserHorizontal.setImageResource(user.friendImage)
                usernameHorizontal.text = user.friendUsername
//                rootHorizontalLayout.setOnClickListener {
//                    navigateToChatFragment(user, holder)
//                }
            }
        }

//        private fun navigateToChatFragment(user: UserModel, holder: ItemHolder) {
//            val action = ChannelFragmentDirections.actionChannelFragmentToChatFragment(user)
//            holder.itemView.findNavController().navigate(action)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            UserChannelHorizontalLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position), holder)
    }

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