package com.braun.karleia.PetServer.agenda

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface AgendaRepository: JpaRepository<Agenda, Long>{
    fun findByDateTime(dateTime: LocalDateTime): List<Agenda>
    @Query("select distinct a from Agenda a" +
            " join a.idServico s" +
            " where LOWER(s.titulo) = LOWER(:titulo)" +
            " order by a.dateTime")
    fun findByTituloServico(@Param("titulo") titulo: String): List<Agenda>
}