package com.example.chatappdemotwo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.chatappdemotwo.R
import com.example.chatappdemotwo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        binding.btnEnter.setOnClickListener {
//            view?.findNavController()?.navigate(R.id.channelFragment)
//        }
        return binding.root
    }

}