package com.braun.karleia.PetServer.servico

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.util.Date

@Entity
@Table(name = "TblServico")
class Servico (

    @Id @GeneratedValue
    var id: Long?= null,

    @NotBlank
    var titulo: String,

    var descricao: String,

){

    constructor() : this(null, "", "" )
}