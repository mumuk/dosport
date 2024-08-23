package com.example.dosport.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    private val _backStack = mutableStateListOf<String>()
    val backStack: List<String> = _backStack

    private val _forwardStack = mutableStateListOf<String>()
    val forwardStack: List<String> = _forwardStack

    fun navigateTo(route: String) {
        _backStack.add(route)
        _forwardStack.clear() // Очистка forwardStack при новом переходе
    }

    fun popBackStack(): String? {
        return if (_backStack.isNotEmpty()) {
            val lastRoute = _backStack.removeLastOrNull()
            lastRoute?.let { _forwardStack.add(it) }
            _backStack.lastOrNull() // Возвращаем текущий маршрут после удаления
        } else {
            null
        }
    }

    fun canNavigateBack(): Boolean = _backStack.size > 1

    fun canNavigateForward(): Boolean = _forwardStack.isNotEmpty()

    fun navigateForward(): String? {
        return if (_forwardStack.isNotEmpty()) {
            val nextRoute = _forwardStack.removeLastOrNull()
            nextRoute?.let { _backStack.add(it) }
            nextRoute
        } else {
            null
        }
    }
}
