package com.example.NoteDemo.SpringBootWithKotin.model

import java.util.*

data class Onboarding(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val content: String = "",
    val imageUrl: String = "",
    val currentStep: String = "INITIAL",
    val isCompleted: Boolean = false,
)
