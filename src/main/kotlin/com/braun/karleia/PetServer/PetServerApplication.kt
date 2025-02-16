package com.braun.karleia.PetServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PetServerApplication

fun main(args: Array<String>) {
	runApplication<PetServerApplication>(*args)
}
