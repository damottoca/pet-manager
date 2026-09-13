package servico

import java.math.BigDecimal

data class Servico(
    val id: Int? = null,
    val nome: String,
    val descricao: String?,
    val preco: BigDecimal
)
