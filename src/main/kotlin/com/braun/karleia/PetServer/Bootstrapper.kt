package com.braun.karleia.PetServer

import com.braun.karleia.PetServer.roles.Role
import com.braun.karleia.PetServer.roles.RoleRepository
import com.braun.karleia.PetServer.users.User
import com.braun.karleia.PetServer.users.UserRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class Bootstrapper(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
): ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val adminRole =
            roleRepository.findByIdOrNull("ADMIN")
                ?: roleRepository.save(Role("ADMIN", "System Administrator"))
                    .also { roleRepository.save(Role("USER", "Premium User")) }

        if (userRepository.findByRole("ADMIN").isEmpty()) {
            val admin = User(
                email="admin@authserver.com",
                password = "admin",
                name="Auth Server Administrator"
            )
            admin.roles.add(adminRole)
            userRepository.save(admin)
        }
    }

}
