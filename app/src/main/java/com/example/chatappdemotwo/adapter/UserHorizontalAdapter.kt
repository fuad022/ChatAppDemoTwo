package com.example.chatappdemotwo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.viewModels
import com.example.chatappdemotwo.databinding.UserChannelHorizontalLayoutBinding
import com.example.chatappdemotwo.model.UserModel
import com.example.chatappdemotwo.ui.channel.ChannelFragmentDirections
import com.example.chatappdemotwo.ui.channel.UserViewModel

class UserHorizontalAdapter : ListAdapter<UserModel, UserHorizontalAdapter.ItemHolder>(DiffCallback()) {

    inner class ItemHolder(private val binding: UserChannelHorizontalLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel: UserModel?) {
            userModel?.let { model ->
                binding.imgUserHorizontal.setImageResource(model.friendImage!!)
                binding.usernameHorizontal.text = model.friendUsername
                binding.root.setOnClickListener {
                    model.let { user -> setOnItemClick?.invoke(user) }
                }
            }
        }
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
        holder.bind(getItem(position))
    }

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
