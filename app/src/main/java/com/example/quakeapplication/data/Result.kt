package com.example.quakeapplication.data

import java.lang.Exception

sealed class Result<out R>
data class Success<T>(val data: T) : Result<T>()
data class Error<Nothing>(val e: Exception): Result<Nothing>()