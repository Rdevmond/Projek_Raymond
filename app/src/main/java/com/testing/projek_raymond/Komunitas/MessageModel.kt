package com.testing.projek_raymond.Komunitas

/**
 * Model data yang merepresentasikan pesan/ulasan dari anggota komunitas.
 * Berisi nama pengirim, isi pesan/ulasan, URL foto avatar, dan biodata profil.
 */
data class MessageModel(
    val senderName: String,
    val messageText: String,
    val avatarUrl: String,
    val bio: String = ""
)
