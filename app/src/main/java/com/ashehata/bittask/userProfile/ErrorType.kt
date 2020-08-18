package com.ashehata.bittask.userProfile

sealed class ErrorType {
    data class ErrorNotFound(var message: String) : ErrorType()
    object NoInternet : ErrorType()
    object NoError : ErrorType()
}