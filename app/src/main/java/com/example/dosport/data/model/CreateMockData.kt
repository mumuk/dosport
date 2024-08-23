import com.example.dosport.data.model.*

fun createMockData(): Triple<ProgramState, ExerciseState, EventState> {
    // Создаем события
    val event1 = Event(id = "1", name = "Event 1", description = "First Event", duration = 3000L, color = "#FF5733")
    val event2 = Event(id = "2", name = "Event 2", description = "Second Event", duration = 5000L, color = "#33FF57")

    // Создаем упражнения и связываем их с событиями
    val exercise1 = Exercise(
        id = "1",
        name = "Exercise 1",
        description = "First Exercise",
        duration = 8000L,
        events = listOf(event1, event2),
        color = "#FF5733"
    )
    val exercise2 = Exercise(
        id = "2",
        name = "Exercise 2",
        description = "Second Exercise",
        duration = 16000L,
        events = listOf(event1, event2, event1, event2),
        color = "#33FF57"
    )
    val exercise3 = Exercise(
        id = "3",
        name = "Exercise 3",
        description = "Third Exercise",
        duration = 24000L,
        events = listOf(event1, event2, event1, event2, event1, event2),
        color = "#5733FF"
    )
    val exercise4 = Exercise(
        id = "4",
        name = "Exercise 4",
        description = "Fourth Exercise",
        duration = 8000L,
        events = listOf(event1, event2),
        color = "#FF3357"
    )

    // Создаем программы и связываем их с упражнениями
    val program1 = Program(
        id = "1",
        name = "Program 1",
        description = "First Program",
        duration = 24000L,
        exercises = listOf(
            exercise1,
//            exercise2
        )
    )
    val program2 = Program(
        id = "2",
        name = "Program 2",
        description = "Second Program",
        duration = 48000L,
        exercises = listOf(
//            exercise1,
//            exercise2,
            exercise4
        )
    )
    val program3 = Program(
        id = "3",
        name = "Program 3",
        description = "Third Program",
        duration = 72000L,
        exercises = listOf(
//            exercise1,
//            exercise2,
            exercise3,
//            exercise4
        )
    )
    val program4 = Program(
        id = "4",
        name = "Program 4",
        description = "Fourth Program",
        duration = 48000L,
        exercises = listOf(
//            exercise1,
            exercise2
        )
    )

    // Возвращаем состояния программ, упражнений и событий
    return Triple(
        ProgramState(programs = listOf(program1, program2, program3, program4)),
        ExerciseState(exercises = listOf(exercise1, exercise2, exercise3, exercise4)),
        EventState(events = listOf(event1, event2))
    )
}



