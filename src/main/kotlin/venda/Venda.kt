package venda

import java.math.BigDecimal
import java.time.LocalDateTime

data class Venda(
    val id: Int? = null,
    val dataHora: LocalDateTime = LocalDateTime.now(),
    val clienteId: Int,
    val animalId: Int,
    val funcionarioId: Int,
    val total: BigDecimal
)