package com.example.dosport.data.model

data class Program(
    val id: String = "",
    val status: String = "private",
    val title: String = "",
    val description: String = "",
    val duration: Long = 0L,
    val exercises: List<String> = emptyList()
)
