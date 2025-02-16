package com.braun.karleia.PetServer.servico

import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ServicoService(
    val servicoRepository: ServicoRepository,
) {
    fun insert(servico: Servico)= servicoRepository.save(servico)
    fun findAll(dir: SortDir): List<Servico> {
        return when(dir) {
            SortDir.ASC -> servicoRepository.findAll(Sort.by("titulo"))
            SortDir.DESC -> servicoRepository.findAll(Sort.by("titulo").descending())
        }
    }

    fun findByIdOrNull(id: Long) =
        servicoRepository.findByIdOrNull(id)

    fun findByTituloOrNull(titulo: String) =
        servicoRepository.findByTitulo(titulo)

    fun delete(id: Long) = servicoRepository.deleteById(id)

}