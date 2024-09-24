package com.example.dosport.data.model

data class Exercise(
    val id: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val createdBy: String = "",
    override val name: String,
    val description: String = "",
    val duration: Long = 0L,
    val events: List<Event> = emptyList(),
    val color: String = "#000000",
    val status: String = Status.PUBLIC.name
) : ListItem

data class ExerciseState(
    val exercises: List<Exercise> = emptyList(),
    val selectedExerciseIndex: Int = 0
)

// Моковые события для упражнений
val event1 = Event(
    id = "1",
    name = "Push Up Start",
    description = "Start with arms fully extended.",
    duration = 10000L, // 10 секунд
    color = "#FF5733"
)
val event2 = Event(
    id = "2",
    name = "Push Up Finish",
    description = "Lower your chest to the ground and push back up.",
    duration = 10000L, // 10 секунд
    color = "#33FF57"
)
val event3 = Event(
    id = "3",
    name = "Sit Up Start",
    description = "Lay flat with your knees bent.",
    duration = 15000L, // 15 секунд
    color = "#0000FF"
)
val event4 = Event(
    id = "4",
    name = "Sit Up Finish",
    description = "Raise your upper body towards your knees.",
    duration = 15000L, // 15 секунд
    color = "#FFFF00"
)
val event5 = Event(
    id = "5",
    name = "Squat Start",
    description = "Start with feet shoulder-width apart.",
    duration = 20000L, // 20 секунд
    color = "#FFA500"
)
val event6 = Event(
    id = "6",
    name = "Squat Finish",
    description = "Lower your hips until thighs are parallel to the ground.",
    duration = 20000L, // 20 секунд
    color = "#8A2BE2"
)
val event7 = Event(
    id = "7",
    name = "Lunges Start",
    description = "Step forward with one leg.",
    duration = 20000L, // 20 секунд
    color = "#FF4500"
)
val event8 = Event(
    id = "8",
    name = "Lunges Finish",
    description = "Lower your hips until both knees are at 90 degrees.",
    duration = 20000L, // 20 секунд
    color = "#2E8B57"
)
val event9 = Event(
    id = "9",
    name = "Plank Start",
    description = "Hold your body in a straight line from head to heels.",
    duration = 60000L, // 60 секунд
    color = "#FF6347"
)
val event10 = Event(
    id = "10",
    name = "Jumping Jacks Start",
    description = "Jump with legs spread wide and arms overhead.",
    duration = 30000L, // 30 секунд
    color = "#4682B4"
)

// Обновленные моки упражнений
val mockExercises = listOf(
    // Упражнения, созданные пользователем с id "1"
    Exercise(
        id = "e1",
        name = "Push Ups",
        description = "Push-ups exercise",
        duration = event1.duration + event2.duration,
        createdBy = "1",
        status = Status.PRIVATE.name,
        events = listOf(event1, event2),
        color = "#FF5733"
    ),
    Exercise(
        id = "e2",
        name = "Sit Ups",
        description = "Sit-ups exercise",
        duration = event3.duration + event4.duration,
        createdBy = "1",
        status = Status.PUBLIC.name,
        events = listOf(event3, event4),
        color = "#33FF57"
    ),

    // Упражнения, созданные друзьями пользователя
    Exercise(
        id = "e3",
        name = "Squats",
        description = "Squats exercise",
        duration = event5.duration + event6.duration,
        createdBy = "2",
        status = Status.FRIENDS.name,
        events = listOf(event5, event6),
        color = "#FFA500"
    ),
    Exercise(
        id = "e4",
        name = "Lunges",
        description = "Lunges exercise",
        duration = event7.duration + event8.duration,
        createdBy = "3",
        status = Status.FRIENDS.name,
        events = listOf(event7, event8),
        color = "#FF4500"
    ),

    // Публичные упражнения
    Exercise(
        id = "e5",
        name = "Plank",
        description = "Plank exercise",
        duration = event9.duration,
        createdBy = "4",
        status = Status.PUBLIC.name,
        events = listOf(event9),
        color = "#FF6347"
    ),
    Exercise(
        id = "e6",
        name = "Jumping Jacks",
        description = "Jumping Jacks exercise",
        duration = event10.duration,
        createdBy = "5",
        status = Status.PUBLIC.name,
        events = listOf(event10),
        color = "#4682B4"
    )
)

// Функция фильтрации доступных упражнений для пользователя
fun getAvailableExercisesForUser(user: User, allExercises: List<Exercise>): List<Exercise> {
    return allExercises.filter { exercise ->
        exercise.status == Status.PUBLIC.name ||
                exercise.createdBy == user.id ||
                (exercise.status == Status.FRIENDS.name && user.friendsIds.contains(exercise.createdBy))
    }
}
