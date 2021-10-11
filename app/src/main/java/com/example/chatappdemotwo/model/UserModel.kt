package com.example.chatappdemotwo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val myUsername: String,
    val friendUsername: String,
    val myImage: Int,
    val friendImage: Int,
    val activeTime: String,
    val isGroup: Boolean
) : Parcelable