package com.braun.karleia.PetServer.servico.controller.responses

import com.braun.karleia.PetServer.servico.Servico
import java.util.Date

data class ServicoResponse(
    val id: Long,
    val titulo: String,
    val descricao: String,
) {
    constructor(servico: Servico): this(
        id=servico.id!!,
        servico.titulo,
        servico.descricao, 
    )
}

