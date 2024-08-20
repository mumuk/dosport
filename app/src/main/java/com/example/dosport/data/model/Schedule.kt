package com.example.dosport.data.model

data class Schedule(
    val id: String = "",
    val programId: String = "",
    val dateTime: Long = 0L,
    val isRecurring: Boolean = false,
    val recurrenceInterval: Long = 0L
)

data class ScheduleState(
    val schedules: List<Schedule> = emptyList(),
    val selectedSchedule: Schedule? = null
)