package org.example.kmpskeleton.domain.util

class UserValidator {
    fun validatePassword(password: String): Result<Unit, PasswordError> {
        if (password.length < 9){
            return Result.Error(PasswordError.TOO_SHORT)
        }

        return Result.Success(Unit   )
    }

    enum class PasswordError: Error {
        TOO_SHORT
    }
}