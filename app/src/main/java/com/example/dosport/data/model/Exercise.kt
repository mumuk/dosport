package com.example.dosport.data.model

data class Exercise(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val duration: Long = 0L,
    val events: List<String> = emptyList()
)


