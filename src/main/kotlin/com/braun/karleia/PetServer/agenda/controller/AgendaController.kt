package com.braun.karleia.PetServer.agenda.controller

import com.braun.karleia.PetServer.agenda.Agenda
import com.braun.karleia.PetServer.agenda.AgendaRepository
import com.braun.karleia.PetServer.agenda.AgendaService
import com.braun.karleia.PetServer.agenda.controller.requests.CreateAgendaRequest
import com.braun.karleia.PetServer.servico.ServicoRepository
import com.braun.karleia.PetServer.users.UserRepository
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/agenda")
class AgendaController(
    val agendaService: AgendaService,
) {
    @GetMapping
    fun getAllAgenda(): List<Agenda> = agendaService.getAllAgenda()

    @PostMapping
    fun insert(@RequestBody agenda: CreateAgendaRequest): Agenda {
        return agendaService.insert(agenda)
    }
}