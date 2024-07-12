package br.com.caju.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity(name = "account")
data class Account(
        @Id
        @Column(nullable = false, length = 3)
        var accountId: String,

        @Column(nullable = false, length = 50)
        var name: String,

        @Column(nullable = false, precision = 6, scale = 2)
        var score: BigDecimal,
)