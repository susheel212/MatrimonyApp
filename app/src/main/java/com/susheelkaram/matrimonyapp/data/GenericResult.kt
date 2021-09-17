package com.susheelkaram.matrimonyapp.data

import java.lang.Exception

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
sealed class GenericResult<T> {
    data class Success<T>(val data: T)
    data class Error(val error: Exception)
}