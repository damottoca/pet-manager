package venda

data class VendaServico(
    val vendaId: Int,
    val servicoId: Int,
    val quantidade: Int = 1
)