package com.braun.karleia.PetServer.users.controller.responses

import com.braun.karleia.PetServer.users.User

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
) {
    constructor(user: User): this(id=user.id!!, user.name, user.email)
}
