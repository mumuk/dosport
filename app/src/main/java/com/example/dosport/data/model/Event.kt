package com.example.dosport.data.model


enum class ScreenType {
    TEXT,
    AUDIO,
    IMAGE,
    VIDEO
}

data class Event(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val duration: Long = 0L,
    val color: String = "",
    val eventScreenId: String = "",
    val screenType: ScreenType? = null,
    val link: String? = null,
    val textColor: String? = null,
    val backgroundColor: String? = null
)




data class EventState(
    val events: List<Event> = emptyList(),
    val selectedEvent: Event? = null
)
