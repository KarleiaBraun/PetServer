package com.braun.karleia.PetServer.users.controller

import com.braun.karleia.PetServer.users.SortDir
import com.braun.karleia.PetServer.users.User
import com.braun.karleia.PetServer.users.UserService
import com.braun.karleia.PetServer.users.controller.requests.CreateUserRequest
import com.braun.karleia.PetServer.users.controller.responses.UserResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
) {
    @GetMapping("/check")
    fun ping() = "Pong"

    @PostMapping
    fun insert(@RequestBody @Valid user: CreateUserRequest) =
        userService.insert(user.toUser())
            .let { UserResponse(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun findAll(@RequestParam dir: String = "ASC", @RequestParam role: String? = null): ResponseEntity<List<UserResponse>> {
        val sortDir = SortDir.findOrNull(dir)
        if (sortDir == null)
            return ResponseEntity.badRequest().build()
        return userService.findAll(sortDir, role)
            .map { UserResponse(it) }
            .let { ResponseEntity.ok(it) }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        userService.findByIdOrNull(id)
            ?.let { UserResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        userService.delete(id)
            .let { ResponseEntity.ok().build() }

    @PutMapping("/{id}/roles/{role}")
    fun grant(@PathVariable id: Long, @PathVariable role: String): ResponseEntity<Void> =
        if (userService.addRole(id, role)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()

}
