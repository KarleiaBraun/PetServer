package com.braun.karleia.PetServer.agenda.controller.responses

import com.braun.karleia.PetServer.agenda.Agenda
import com.braun.karleia.PetServer.servico.Servico
import com.braun.karleia.PetServer.users.User
import java.time.LocalDateTime

data class AgendaResponse(
    val id: Long,
    val idUser: User,
    val idServico: Servico,
    val datetime: LocalDateTime,
){
    constructor(agenda: Agenda): this(
        id=agenda.id!!,
        agenda.idUser!!,
        agenda.idServico!!,
        agenda.dateTime,
    )
}
