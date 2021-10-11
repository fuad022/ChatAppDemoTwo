package com.example.chatappdemotwo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatModel(
//    val titleName: String,
//    val myImage: Int,
    val image: Int,
    val message: String,
    val isMe: Boolean
) : Parcelable
