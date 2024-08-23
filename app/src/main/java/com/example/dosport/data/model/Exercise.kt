package com.example.dosport.data.model

data class Exercise(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val duration: Long = 0L,
    val events: List<Event> = emptyList(),
    val color: String = "#000000"
)

data class ExerciseState(
    val exercises: List<Exercise> = emptyList(),
    val selectedExerciseIndex: Int = 0
)
