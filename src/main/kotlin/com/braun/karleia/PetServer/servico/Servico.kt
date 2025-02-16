package com.braun.karleia.PetServer.servico

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.util.Date
import kotlin.time.Duration

@Entity
@Table(name = "TblServico")
class Servico (

    @Id @GeneratedValue
    var id: Long?= null,

    @NotBlank
    var titulo: String,

    var descricao: String,

    @Column(nullable = false)
    var preco: Double

){
    constructor() : this(null, "", "",  0.00 )
}