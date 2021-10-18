package com.example.chatappdemotwo.ui.chat

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatappdemotwo.R
import com.example.chatappdemotwo.adapter.ChatAdapter
import com.example.chatappdemotwo.databinding.FragmentChatBinding
import com.example.chatappdemotwo.model.ChatModel

class ChatFragment : Fragment() {
    private val binding by lazy { FragmentChatBinding.inflate(layoutInflater) }
    private val args: ChatFragmentArgs by navArgs()
    private var chatModelList = arrayListOf<ChatModel>()
    private var index = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initToolbar()
        clickSendBtn()
        return binding.root
    }

    private fun initToolbar() {
        binding.apply {
            setHasOptionsMenu(true)
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbarChat)

            if (args.userModel.isGroup!!) {
                toolbarChat.title = "GROUP"
            } else {
                toolbarChat.title = args.userModel.friendUsername

                val activeStatus = if (args.userModel.isFriendOnline!!) "Active Now" else "Offline"
                toolbarChat.subtitle = activeStatus

                toolbarChat.isSubtitleCentered = true
                toolbarChat.setSubtitleTextColor(Color.WHITE)
            }

            toolbarChat.setNavigationOnClickListener {
                findNavController().navigate(R.id.action_chatFragment_to_channelFragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settings_menu, menu)
    }

    private fun clickSendBtn() {
        binding.apply {
            sendBtn.setOnClickListener {
                index++
                val message = edt.text.toString()
                if (message.isEmpty()) {
                    Toast.makeText(it.context, "Start typing", Toast.LENGTH_SHORT).show()
                } else {
                    if (index % 2 == 0) {
                        sendMessageData(message, args.userModel.friendImage!!, false)
                    } else {
                        sendMessageData(message, args.userModel.myImage!!, true)
                    }
                    edt.setText("")
                }
            }
        }
    }

    private fun sendMessageData(message: String, image: Int, isMe: Boolean) {
        val chatData = ChatModel(image, message, isMe, args.userModel.isFriendOnline!!)
        chatModelList.add(chatData)
        setupChatAdapter(chatModelList)
    }

    private fun setupChatAdapter(list: ArrayList<ChatModel>) {
        ChatAdapter().apply {
            submitList(list.toMutableList())
            binding.chatRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity).apply { stackFromEnd = true; reverseLayout = false }
            }
            this.itemCount.let { binding.chatRecyclerView.scrollToPosition(it.minus(1)) }
            binding.chatRecyclerView.adapter = this
        }
    }
}















