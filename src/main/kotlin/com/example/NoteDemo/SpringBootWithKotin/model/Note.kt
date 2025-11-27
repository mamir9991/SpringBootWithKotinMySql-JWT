package com.example.NoteDemo.SpringBootWithKotin.model

import java.util.UUID

data class Note(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val content: String = ""
)
