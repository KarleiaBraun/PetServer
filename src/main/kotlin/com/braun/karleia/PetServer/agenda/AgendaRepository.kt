package com.braun.karleia.PetServer.agenda

import com.braun.karleia.PetServer.servico.Servico
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface AgendaRepository: JpaRepository<Agenda, Long>