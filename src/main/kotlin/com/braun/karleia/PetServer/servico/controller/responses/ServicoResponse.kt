package com.braun.karleia.PetServer.servico.controller.responses

import com.braun.karleia.PetServer.servico.Servico

data class ServicoResponse(
    val id: Long,
    val titulo: String,
    val descricao: String,
    val preco: Double
) {
    constructor(servico: Servico): this(
        id=servico.id!!,
        servico.titulo,
        servico.descricao,
        servico.preco.toDouble(),
    )
}

