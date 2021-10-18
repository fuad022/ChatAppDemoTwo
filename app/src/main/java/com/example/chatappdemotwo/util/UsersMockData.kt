package com.example.chatappdemotwo.util

import com.example.chatappdemotwo.R
import com.example.chatappdemotwo.model.UserModel
import java.util.*

class UsersMockData {
    fun usersData(): List<UserModel> {
        val usersList = arrayListOf<UserModel>()
        val imagesList = listOf(
            R.drawable.photo_1,
            R.drawable.photo_2,
            R.drawable.photo_3,
            R.drawable.photo_4,
            R.drawable.photo_5,
            R.drawable.photo_6,
            R.drawable.photo_7
        )

        listOf(
            UserModel("Frank", "Marta", imagesList[0], imagesList[1], currentTime(), false, true),
            UserModel("Frank", "Francis", imagesList[0], imagesList[3], currentTime(), true, true),
            UserModel("Frank", "Gonzales", imagesList[0], imagesList[5], currentTime(), false, false),
            UserModel("Frank", "Martina", imagesList[0], imagesList[2], currentTime(), true, false)
        ).forEach {
            usersList.add(it)
        }
        return usersList
    }

    private fun currentTime(): String {
        val calendar = Calendar.getInstance()
        val time = "${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}"
        return time
    }
}