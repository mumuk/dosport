package com.example.dosport.data.model


enum class ScreenType {
    TEXT,
    AUDIO,
    IMAGE,
    VIDEO
}


data class Event(
    val id: String = "",
    override val name: String,
    val description: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val createdBy: String = "",
    val duration: Long = 0L,
    val color: String = "#FFFFFF",
    val eventScreenId: String = "",
    val screenType: ScreenType? = null,
    val link: String? = null,
    val status: String = Status.PUBLIC.name
) : ListItem


data class EventState(
    val events: List<Event> = emptyList(),
    val selectedEventIndex: Int = 0,
)
