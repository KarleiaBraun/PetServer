package com.braun.karleia.PetServer.servico

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ServicoRepository: JpaRepository<Servico, Long> {
    fun findByTitulo(titulo: String): Servico?
    @Query("select distinct s from Servico s" +
            " where s.titulo = :titulo" +
            " order by s.titulo")
    fun findByRole(role: String): List<Servico>
}