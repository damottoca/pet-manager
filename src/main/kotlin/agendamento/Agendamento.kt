package agendamento

import java.time.LocalDateTime

data class Agendamento(
    val id: Int? = null,
    val dataHora: LocalDateTime,
    val animalId: Int,
    val animalNome: String? = null,
    val servicoId: Int,
    val servicoNome: String? = null,
    val funcionarioId: Int,
    val funcionarioNome: String? = null,
    val observacao: String?
)