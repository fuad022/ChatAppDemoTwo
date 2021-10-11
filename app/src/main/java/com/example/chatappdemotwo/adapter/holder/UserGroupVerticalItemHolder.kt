package com.example.chatappdemotwo.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappdemotwo.databinding.UsersGroupChannelVerticalLayoutBinding
import com.example.chatappdemotwo.model.UserModel
import com.example.chatappdemotwo.ui.channel.ChannelFragmentDirections

class UserGroupVerticalItemHolder(private val binding: UsersGroupChannelVerticalLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: UserModel) {
        binding.apply {
            imgMyUserGroupVertical.setImageResource(user.myImage)
            imgFriendUserGroupVertical.setImageResource(user.friendImage)
            myNameGroupVertical.text = user.myUsername
            friendNameGroupVertical.text = user.friendUsername
            lastActiveTimeGroupVertical.text = user.activeTime
//            rootGroupVerticalLayout.setOnClickListener {
//                navigateToChatFragment(user, holder)
//            }
        }

    }

//    private fun navigateToChatFragment(user: UserModel, holder: UserGroupVerticalItemHolder) {
//        val action = ChannelFragmentDirections.actionChannelFragmentToChatFragment(user)
//        holder.itemView.findNavController().navigate(action)
//    }

    companion object {
        fun from(parent: ViewGroup): UserGroupVerticalItemHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                UsersGroupChannelVerticalLayoutBinding.inflate(layoutInflater, parent, false)
            return UserGroupVerticalItemHolder(binding)
        }
    }
}