package com.braun.karleia.PetServer.users

enum class SortDir {
    ASC, DESC;

    companion object {
        fun findOrNull(sortDir: String) =
            entries.find { it.name == sortDir.uppercase() }
    }
}
