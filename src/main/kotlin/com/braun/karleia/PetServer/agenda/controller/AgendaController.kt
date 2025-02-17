package com.braun.karleia.PetServer.agenda.controller

import com.braun.karleia.PetServer.agenda.Agenda
import com.braun.karleia.PetServer.agenda.AgendaService
import com.braun.karleia.PetServer.agenda.controller.requests.CreateAgendaRequest
import com.braun.karleia.PetServer.agenda.controller.responses.AgendaResponse
import com.braun.karleia.PetServer.users.SortDir
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RestController
@RequestMapping("/agenda")
class AgendaController(
    val agendaService: AgendaService,
) {

    @GetMapping
    fun findAll(@RequestParam dir: String = "ASC", @RequestParam titulo: String? = null): ResponseEntity<List<AgendaResponse>> {
        val sortDir = SortDir.findOrNull(dir)
        if (sortDir == null)
            return ResponseEntity.badRequest().build()
        return agendaService.findAll(sortDir, titulo)
            .map { AgendaResponse(it) }
            .let { ResponseEntity.ok(it) }
    }

    @PostMapping
    fun insert(@RequestBody agenda: CreateAgendaRequest): Agenda {
        return agendaService.insert(agenda)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        agendaService.findByIdOrNull(id)
            ?.let { AgendaResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        agendaService.delete(id)
            .let { ResponseEntity.ok().build() }

    @GetMapping("/datetime/{date}")
    fun getByDate(@PathVariable date: String): List<Agenda> {
        //val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val brFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm",  Locale.of("pt", "BR"))

        val dateTime = LocalDateTime.parse(date, brFormatter)
        return agendaService.findByDate(dateTime)
    }
}