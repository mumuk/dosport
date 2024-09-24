package com.example.dosport.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class AppState(
    val isLoading: Boolean = true,
    val userState: @RawValue UserState = UserState(),
    val programState: @RawValue ProgramState = ProgramState(),
    val exerciseState: @RawValue ExerciseState = ExerciseState(),
    val scheduleState: @RawValue ScheduleState = ScheduleState(),
    val eventState: @RawValue EventState = EventState(),
    val errorState: @RawValue ErrorState = ErrorState(),  // Добавлен новый ErrorState
    val currentScreen: @RawValue Screen = Screen.Login,
    val draftProgram: @RawValue Program? = null
) : Parcelable
