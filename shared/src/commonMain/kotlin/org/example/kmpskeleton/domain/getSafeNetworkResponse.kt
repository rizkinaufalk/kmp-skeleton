package org.example.kmpskeleton.domain

import io.github.aakira.napier.Napier
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

suspend inline fun <reified T> getSafeNetworkResponse(
    onSuccessOperations: (NetworkResponse.Success<T>) -> Unit = {},
    networkOperation: () -> HttpResponse,
): NetworkResponse<T> {
    return try {
        val response = networkOperation()
        Napier.d("NetworkResponse: $response")
        when (response.status) {
            HttpStatusCode.OK -> {
                NetworkResponse.Success<T>(response.body()).apply(onSuccessOperations)
            }

            HttpStatusCode.Unauthorized -> {
                NetworkResponse.Unauthorized(Throwable("Failed to authorize"))
            }

            else -> {
                val responseMsg = response.body<ApiResponse<Unit>>().message
                NetworkResponse.Failure(Exception(responseMsg))
            }
        }
    } catch (e: Exception) {
        Napier.e(e.message.orEmpty())
        when (e) {
            is NoTransformationFoundException -> NetworkResponse.Failure(
                Throwable(
                    message = "Invalid response sent from the backend. It was probably supposed to" +
                            " send an object of type 'ApiResponse' but seems like it didn't. Please" +
                            " verify it with your backend engineers."
                )
            )

            else -> NetworkResponse.Failure(e)
        }
    }
}