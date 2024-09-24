package com.example.dosport.data.model


enum class Status {
    PRIVATE,
    FRIENDS,
    PUBLIC,

}

data class Program(
    val id: String = "",
    val status: String = Status.PUBLIC.name,
    override val name: String,
    val description: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val createdBy: String = "",
    val duration: Long = 0L,
    val exercises: List<Exercise> = emptyList()
): ListItem


data class ProgramState(
    val programs: List<Program> = emptyList(),
    val selectedProgram: Program? = null
)


//val event1 =
//    Event(id = "1", name = "Left turn ", description = "", duration = 30000L, color = "#FF5733")
//val event2 = Event(
//    id = "2",
//    name = "Right turn ",
//    description = "Be careful with the right turn!",
//    duration = 30000L,
//    color = "#33FF57"
//)
//val event3 = Event(
//    id = "3",
//    name = "Chin Lift ",
//    description = "Don't push to the point of pain or discomfort",
//    duration = 30000L,
//    color = "#0000FF"
//)
//val event4 = Event(
//    id = "4",
//    name = "Chin Tilt ",
//    description = "Don't let your back lean forward",
//    duration = 30000L,
//    color = "#FFFF00"
//)
//val exercise1 = Exercise(
//    id = "1",
//    name = "Head Turns to the Sides",
//    description = "Keep your back straight, turn your head to the sides, looking up only with your eyes, without lifting your head.",
//    duration = 8000L,
//    events = listOf(event1, event2),
//    color = "#FF5733"
//)
//val exercise2 = Exercise(
//    id = "2",
//    name = "Tilt and Lift Your Head",
//    description = "Keep your back straight and alternately lift and lower your chin without leaning forward.",
//    duration = 16000L,
//    events = listOf(event3, event4, event3, event4),
//    color = "#33FF57"
//)

//data class ProgramState(
//    val programs: List<Program> = emptyList(),
//    val selectedProgram: Program? = Program(
//        id = "1",
//        name = "Daily Morning exercises",
//        description = "First Program",
//        duration = 24000L,
//        exercises = listOf(exercise1, exercise2)
//    )
//)
