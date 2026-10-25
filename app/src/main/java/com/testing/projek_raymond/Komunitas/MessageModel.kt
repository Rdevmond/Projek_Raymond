package com.testing.projek_raymond.Komunitas

data class MessageModel(
    val senderName: String,
    val messageText: String,
    val avatarUrl: String,
    val bio: String = ""
)
