package com.braun.karleia.PetServer.agenda

import com.braun.karleia.PetServer.agenda.controller.requests.CreateAgendaRequest
import com.braun.karleia.PetServer.servico.ServicoRepository
import com.braun.karleia.PetServer.users.SortDir
import com.braun.karleia.PetServer.users.User
import com.braun.karleia.PetServer.users.UserRepository
import jakarta.validation.Valid
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.time.LocalDateTime
import java.util.Date

@Service
class AgendaService(
    val agendaRepository: AgendaRepository,
    private val userRepository: UserRepository,
    private val servicoRepository: ServicoRepository
){
    fun getAllAgenda(): List<Agenda> = agendaRepository.findAll()

    fun findAll(dir: SortDir, titulo: String?): List<Agenda> {
        if (!titulo.isNullOrBlank())
            return agendaRepository.findByTituloServico(titulo)
        return when(dir) {
            SortDir.ASC -> agendaRepository.findAll(Sort.by("datetime"))
            SortDir.DESC -> agendaRepository.findAll(Sort.by("datetime").descending())
        }
    }

    fun insert(@RequestBody @Valid agenda: CreateAgendaRequest ): Agenda {
        val user = userRepository.findById(agenda.idUser)
            .orElseThrow { IllegalArgumentException("Usuário não encontrado") }

        val servico = servicoRepository.findById(agenda.idServico)
            .orElseThrow { IllegalArgumentException("Serviço não encontrado") }

        val agenda = agenda.toAgenda(user, servico)
        return agendaRepository.save(agenda)
    }

    fun findByIdOrNull(id: Long) =
        agendaRepository.findByIdOrNull(id)

    fun delete(id: Long) = agendaRepository.deleteById(id)

    fun findByDate(date: LocalDateTime) =
        agendaRepository.findByDateTime(date)


}