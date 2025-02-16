package com.braun.karleia.PetServer.agenda.controller.requests

import com.braun.karleia.PetServer.agenda.Agenda
import com.braun.karleia.PetServer.servico.Servico
import com.braun.karleia.PetServer.users.User
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

data class CreateAgendaRequest(
    @field:NotNull
    val idUser: Long,

    @field:NotNull
    val idServico: Long,

    @field:NotBlank
     val dateTime: LocalDateTime
){
    fun toAgenda (user: User, servico: Servico) = Agenda(
        idUser = user,
        idServico = servico,
        dateTime = dateTime
    )
}
