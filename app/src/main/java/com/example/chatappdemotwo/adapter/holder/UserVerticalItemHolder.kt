package com.example.chatappdemotwo.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappdemotwo.databinding.UserChannelVerticalLayoutBinding
import com.example.chatappdemotwo.model.UserModel
import com.example.chatappdemotwo.ui.channel.ChannelFragmentDirections

class UserVerticalItemHolder(private val binding: UserChannelVerticalLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: UserModel) {
        binding.apply {
            imgFriendVertical.setImageResource(user.friendImage)
            usernameFriendVertical.text = user.friendUsername
            lastActiveTimeVertical.text = user.activeTime
//            rootVerticalLayout.setOnClickListener {
//                navigateToChatFragment(user, holder)
//            }
        }
    }

//    private fun navigateToChatFragment(user: UserModel, holder: UserVerticalItemHolder) {
//        val action = ChannelFragmentDirections.actionChannelFragmentToChatFragment(user)
//        holder.itemView.findNavController().navigate(action)
//    }

    companion object {
        fun from(parent: ViewGroup): UserVerticalItemHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = UserChannelVerticalLayoutBinding.inflate(layoutInflater, parent, false)
            return UserVerticalItemHolder(binding)
        }
    }
}