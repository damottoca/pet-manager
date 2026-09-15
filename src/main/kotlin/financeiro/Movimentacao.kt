package financeiro

import enums.TipoMovimentacao
import java.math.BigDecimal
import java.time.LocalDateTime

data class Movimentacao(
    val id: Int? = null,
    val valor: BigDecimal,
    val tipo: TipoMovimentacao,
    val pagador: String,
    val recebedor: String,
    val dataHora: LocalDateTime = LocalDateTime.now(),
    val descricao: String,
    val responsavelId: Int
)