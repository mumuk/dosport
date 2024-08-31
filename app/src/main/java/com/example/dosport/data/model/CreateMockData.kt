import com.example.dosport.data.model.*

fun createMockData(): Triple<ProgramState, ExerciseState, EventState> {
    // Создаем события для первого упражнения
    val event1 = Event(
        id = "1",
        name = "Left Turn",
        description = "Turn your head to the left.",
        duration = 8000L,
        color = "#FF5733"
    )
    val event2 = Event(
        id = "2",
        name = "Right Turn",
        description = "Turn your head to the right.",
        duration = 8000L,
        color = "#33FF57"
    )

    // Создаем события для второго упражнения
    val event3 = Event(
        id = "3",
        name = "Chin Lift",
        description = "Lift your chin up.",
        duration = 7000L,
        color = "#0000FF"
    )
    val event4 = Event(
        id = "4",
        name = "Chin Tilt",
        description = "Tilt your chin down.",
        duration = 7000L,
        color = "#FFFF00"
    )

    // Создаем события для третьего упражнения
    val event5 = Event(
        id = "5",
        name = "Shoulder Shrug",
        description = "Raise your shoulders towards your ears.",
        duration = 6000L,
        color = "#FFA500"
    )
    val event6 = Event(
        id = "6",
        name = "Shoulder Roll",
        description = "Roll your shoulders back.",
        duration = 6000L,
        color = "#8A2BE2"
    )

    // Создаем события для четвертого упражнения
    val event7 = Event(
        id = "7",
        name = "Arm Stretch",
        description = "Stretch your arms out to the sides.",
        duration = 10000L,
        color = "#FF4500"
    )
    val event8 = Event(
        id = "8",
        name = "Wrist Circles",
        description = "Rotate your wrists.",
        duration = 10000L,
        color = "#2E8B57"
    )

    // Создаем упражнения и связываем их с событиями
    val exercise1 = Exercise(
        id = "1",
        name = "Head Turns to the Sides",
        description = "Keep your back straight, turn your head to the sides, looking up only with your eyes, without lifting your head.",
        duration = 16000L,
        events = listOf(event1, event2),
        color = "#FF5733"
    )
    val exercise2 = Exercise(
        id = "2",
        name = "Tilt and Lift Your Head",
        description = "Keep your back straight and alternately lift and lower your chin without leaning forward.",
        duration = 14000L,
        events = listOf(event3, event4),
        color = "#33FF57"
    )
    val exercise3 = Exercise(
        id = "3",
        name = "Shoulder Exercises",
        description = "Perform shoulder shrugs and rolls to loosen up.",
        duration = 12000L,
        events = listOf(event5, event6),
        color = "#FFA500"
    )
    val exercise4 = Exercise(
        id = "4",
        name = "Arm and Wrist Stretches",
        description = "Stretch your arms and wrists to relieve tension.",
        duration = 20000L,
        events = listOf(event7, event8),
        color = "#FF4500"
    )

    // Создаем программы и связываем их с упражнениями
    val program1 = Program(
        id = "1",
        name = "Morning Routine",
        description = "A quick set of exercises to start your day.",
        duration = 30000L,
        exercises = listOf(
            exercise1,
            exercise2
        )
    )
    val program2 = Program(
        id = "2",
        name = "Office Stretch",
        description = "Stretching exercises to do at your desk.",
        duration = 34000L,
        exercises = listOf(
            exercise3,
            exercise4
        )
    )
    val program3 = Program(
        id = "3",
        name = "Full Body Relaxation",
        description = "A full set of exercises to relax after a long day.",
        duration = 54000L,
        exercises = listOf(
            exercise1,
            exercise2,
            exercise3,
            exercise4
        )
    )
    val program4 = Program(
        id = "4",
        name = "Evening Wind-Down",
        description = "Exercises to help you wind down before bed.",
        duration = 32000L,
        exercises = listOf(
            exercise2,
            exercise3
        )
    )

    // Возвращаем состояния программ, упражнений и событий
    return Triple(
        ProgramState(programs = listOf(program1, program2, program3, program4)),
        ExerciseState(exercises = listOf(exercise1, exercise2, exercise3, exercise4)),
        EventState(events = listOf(event1, event2, event3, event4, event5, event6, event7, event8))
    )
}
