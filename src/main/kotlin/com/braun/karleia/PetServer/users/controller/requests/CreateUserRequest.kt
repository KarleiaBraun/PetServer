package com.braun.karleia.PetServer.users.controller.requests

import com.braun.karleia.PetServer.users.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class CreateUserRequest(
    @field:Email
    @field:NotNull
    val email: String?,

    @field:NotBlank
    val password: String?,

    @field:NotBlank
    val name: String?
) {
    fun toUser() = User(
        email = email!!,
        password = password!!,
        name = name!!
    )
}
