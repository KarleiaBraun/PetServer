package com.braun.karleia.PetServer.agenda

import com.braun.karleia.PetServer.servico.Servico
import com.braun.karleia.PetServer.users.User
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.UniqueElements
import java.time.LocalDateTime

@Entity
@Table(name = "Agenda")
class Agenda(
    @Id
    @GeneratedValue
    var id: Long?= null,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var idUser: User,

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    var idServico: Servico,

    @NotNull
    var dateTime: LocalDateTime
) {
    constructor() : this(null, User(), Servico(), LocalDateTime.now())
}