package com.example.chatappdemotwo.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatappdemotwo.adapter.ChatAdapter
import com.example.chatappdemotwo.databinding.FragmentChatBinding
import com.example.chatappdemotwo.model.ChatModel

class ChatFragment : Fragment() {
    private val binding by lazy { FragmentChatBinding.inflate(layoutInflater) }
    private val args: ChatFragmentArgs by navArgs()
    private var chatAdapter = ChatAdapter()
    private var chatModelList = ArrayList<ChatModel>()
    private var index = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init()
        return binding.root
    }

    private fun init() {
        binding.apply {
            setHasOptionsMenu(true)
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbarChat)
            if (args.userModel.isGroup) {
                binding.toolbarChat.title = "GROUP"
            } else {
                binding.toolbarChat.title = args.userModel.friendUsername
            }

            clickSendBtn()
        }
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
                        sendMessageData(message, args.userModel.friendImage, false)
                    } else {
                        sendMessageData(message, args.userModel.myImage, true)
                    }
                    edt.setText("")
                }
            }
        }
    }

    private fun sendMessageData(message: String, image: Int, isMe: Boolean) {
        val chatData = ChatModel(image, message, isMe)
//        if (isMe) {
//            chatAdapter = ChatAdapter(isMe)
//            chatModelList.add(chat)
//        } else {
//            chatModelList.add(chat)
//        }
//        chatAdapter.submitList(chatModelList)
        chatModelList.add(chatData)
        setupChatAdapter(chatModelList)
    }

    private fun setupChatAdapter(list: ArrayList<ChatModel>) {
        ChatAdapter().apply {
            submitList(list.toMutableList())
            binding.chatRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity).apply {
                    stackFromEnd = true
                    reverseLayout = true
                }
            }
            binding.chatRecyclerView.adapter = this
        }
    }

}















