package com.braun.karleia.PetServer.agenda

import com.braun.karleia.PetServer.agenda.controller.requests.CreateAgendaRequest  
import com.braun.karleia.PetServer.servico.ServicoRepository
import com.braun.karleia.PetServer.users.UserRepository
import jakarta.validation.Valid
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class AgendaService(
    val agendaRepository: AgendaRepository,
    private val userRepository: UserRepository,
    private val servicoRepository: ServicoRepository
){
    fun getAllAgenda(): List<Agenda> = agendaRepository.findAll()

    fun insert(@RequestBody @Valid agenda: CreateAgendaRequest ): Agenda {
        val user = userRepository.findById(agenda.idUser)
            .orElseThrow { IllegalArgumentException("Usuário não encontrado") }

        val servico = servicoRepository.findById(agenda.idServico)
            .orElseThrow { IllegalArgumentException("Serviço não encontrado") }

        val agenda = agenda.toAgenda(user, servico)
        return agendaRepository.save(agenda)
    }
}