package com.braun.karleia.PetServer.servico

import com.braun.karleia.PetServer.servico.controller.requests.CreateServicoRequest
import com.braun.karleia.PetServer.servico.controller.responses.ServicoResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/servico")
class ServicoController (
    val servicoService: ServicoService,
) {

    @PostMapping
    fun insert(@RequestBody @Valid servico: CreateServicoRequest ) =
        servicoService.insert(servico.toServico())
            .let { ServicoResponse(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun findAll(@RequestParam dir: String = "ASC"): ResponseEntity<List<ServicoResponse>> {
        val sortDir = SortDir.findOrNull(dir)
        if (sortDir == null)
            return ResponseEntity.badRequest().build()
        return servicoService.findAll(sortDir)
            .map { ServicoResponse(it) }
            .let { ResponseEntity.ok(it) }
    }

    @GetMapping("/titulo/{titulo}")
    fun getByTitulo(@PathVariable titulo: String) =
        servicoService.findByTituloOrNull(titulo)
            ?.let { ServicoResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        servicoService.findByIdOrNull(id)
            ?.let { ServicoResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        servicoService.delete(id)
            .let { ResponseEntity.ok().build() }
}
