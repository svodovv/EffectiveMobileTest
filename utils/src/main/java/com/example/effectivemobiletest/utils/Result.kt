package com.example.effectivemobiletest.utils

sealed interface Result<T> {
    val data: T? get() = null
    val message: String? get() = null

    class Success<T>(override val data: T) : Result<T>
    class Error<T>(override val message: String?) : Result<T>

}

