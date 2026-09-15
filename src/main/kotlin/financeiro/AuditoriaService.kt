package financeiro

class AuditoriaService(
    private val movimentacaoService: MovimentacaoService
) {

    fun auditar() {

        println()
        println("====== AUDITORIA FINANCEIRA ======")

        val movimentacoes = movimentacaoService.listar()

        if (movimentacoes.isEmpty()) {
            println("Nenhuma movimentação financeira encontrada.")
            return
        }

        for (movimentacao in movimentacoes) {

            println()
            println("ID: ${movimentacao.id}")
            println("Tipo: ${movimentacao.tipo}")
            println("Valor: R$ ${movimentacao.valor}")
            println("Pagador: ${movimentacao.pagador}")
            println("Recebedor: ${movimentacao.recebedor}")
            println("Data/Hora: ${movimentacao.dataHora}")
            println("Descrição: ${movimentacao.descricao}")
            println("Responsável: ${movimentacao.responsavelId}")
        }
    }
}