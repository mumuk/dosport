package com.example.dosport.data.model

data class User(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val avatar: String = "",
    val email: String = "",
    val password: String = "",
    val uiLanguage: String = "en",
    val friendsIds: List<String> = emptyList(),
)

data class UserState(
    val user: User? = null,
    val isLoggedIn: Boolean = false
)