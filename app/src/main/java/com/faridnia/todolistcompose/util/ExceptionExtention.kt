package com.faridnia.todolistcompose.util

import com.faridnia.todolistcompose.Constants
import retrofit2.HttpException
import java.io.IOException
import java.net.ProtocolException
import java.net.SocketTimeoutException

fun Exception.extractErrorMessageFromResponse(): String {
    when (this) {
        is HttpException -> {
            when {

                code() == 400 || code() == 500 || code() == 422 || code() == 401-> {
                    return handleServerErrors()
                }

                code() == 404 -> {
                    return handleServerErrors()//"Requested page is not available"
                }

                code() == 403 -> {
                    return handle403Errors()
                }

            }
        }

        is SocketTimeoutException -> {
            return "Timeout"
        }

        is ProtocolException -> {
            return Constants.DEFAULT_ERROR
        }

        is IOException -> {
            return "Couldn't reach server. Check your internet connection."
        }

        else -> {
            return this.message ?: Constants.DEFAULT_ERROR
        }
    }

    return Constants.DEFAULT_ERROR
}

fun HttpException.handle403Errors(): String {
    return Constants.DEFAULT_ERROR
}

private fun HttpException.handleServerErrors(): String {
    return Constants.DEFAULT_ERROR
}
