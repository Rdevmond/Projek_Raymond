package com.testing.projek_raymond.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val category: String,
    val quantity: String,
    val price: String,
    val content: String,
    val createdAt: Long = System.currentTimeMillis()
)
