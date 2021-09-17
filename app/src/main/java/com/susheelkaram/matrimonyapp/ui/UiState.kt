package com.susheelkaram.matrimonyapp.ui

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
sealed class UiState {
    data class Success<T>(val data: T) : UiState()
    data class Error(val error: Exception) : UiState()
    object Loading : UiState()
}

sealed class UiEvent {
    data class Message(val message: String) : UiEvent()
}
