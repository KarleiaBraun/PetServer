package com.braun.karleia.PetServer.servico.controller.requests
import com.braun.karleia.PetServer.servico.Servico
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.util.Date

data class CreateServicoRequest(

    @field:NotNull
    val titulo: String?,

    @field:NotBlank
    val descricao: String?,

    @field:NotNull
    val preco: Double?,
) {
    fun toServico () = Servico(
        titulo = titulo!!,
        descricao = descricao!!,
        preco = preco!!
    )
}
