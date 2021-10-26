package com.example.chatappdemotwo.ui.chat

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.chatappdemotwo.R
import com.example.chatappdemotwo.adapter.ChatAdapter
import com.example.chatappdemotwo.databinding.FragmentChatBinding
import com.example.chatappdemotwo.model.ChatModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : Fragment() {
    private val binding by lazy { FragmentChatBinding.inflate(layoutInflater) }
    private val args: ChatFragmentArgs by navArgs()
    private var chatModelList = arrayListOf<ChatModel>()
    private val viewModel: ChatViewModel by viewModel()
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
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarChat)

        if (args.userModel.isGroup!!) {
            binding.toolbarChat.title = "GROUP"
        } else {
            binding.toolbarChat.title = args.userModel.friendUsername

            val activeStatus = if (args.userModel.isFriendOnline!!) "Active Now" else "Offline"
            binding.toolbarChat.subtitle = activeStatus
        }
        binding.toolbarChat.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_chatFragment_to_channelFragment)

            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settings_menu, menu)
    }

    private fun clickSendBtn() {
        binding.sendBtn.setOnClickListener {
            index++
            val message = binding.edt.text.toString()
            if (message.isEmpty()) {
                Toast.makeText(it.context, "Start typing", Toast.LENGTH_SHORT).show()
            } else {
                if (index % 2 == 0)
                    viewModel.sendMessageData(ChatModel(args.userModel.friendImage!!, message, false, args.userModel.isFriendOnline!!))
                else
                    viewModel.sendMessageData(ChatModel(args.userModel.myImage!!, message, true, args.userModel.isFriendOnline!!))
                binding.edt.setText("")
            }
        }
        observeChatMessage()
    }

    private fun observeChatMessage() {
        viewModel.chatDataList.observe(viewLifecycleOwner, {
            ChatAdapter().apply {
                chatModelList.add(it)
                submitList(chatModelList.toMutableList())
                this.itemCount.let { binding.chatRecyclerView.scrollToPosition(it.minus(1)) }
                binding.chatRecyclerView.adapter = this
            }
        })
    }
}
