package com.example.chatappdemotwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.chatappdemotwo.databinding.ActivityMainBinding
import com.example.chatappdemotwo.ui.channel.ChannelFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}




















