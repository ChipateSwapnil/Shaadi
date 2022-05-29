package com.demo.shaadi.data.remote.response

sealed class NetworkResponse {
    object Loading : NetworkResponse()
    class Success<T>(val data: T) : NetworkResponse()
    class Error(val errorMessage: String) : NetworkResponse()
    class ConnectionError(val errorMessage: String) : NetworkResponse()
}