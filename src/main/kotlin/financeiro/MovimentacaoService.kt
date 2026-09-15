package financeiro

import enums.TipoMovimentacao
import java.math.BigDecimal

class MovimentacaoService(
    private val movimentacaoDAO: MovimentacaoDAO
) {

    fun registrar(movimentacao: Movimentacao) {

        if (movimentacao.valor <= BigDecimal.ZERO) {
            println("O valor deve ser maior que zero.")
            return
        }

        if (movimentacao.pagador.isBlank()) {
            println("O pagador é obrigatório.")
            return
        }

        if (movimentacao.recebedor.isBlank()) {
            println("O recebedor é obrigatório.")
            return
        }

        if (movimentacao.descricao.isBlank()) {
            println("A descrição é obrigatória.")
            return
        }

        if (movimentacao.responsavelId <= 0) {
            println("O responsável pela movimentação é inválido.")
            return
        }

        try {

            val saldoAtual = movimentacaoDAO.saldo()

            val caixa = Caixa(saldoAtual)

            if (movimentacao.tipo == TipoMovimentacao.ENTRADA) {

                caixa.adicionar(movimentacao.valor)

            } else {

                caixa.retirar(movimentacao.valor)
            }

            movimentacaoDAO.cadastrar(movimentacao)

            movimentacaoDAO.atualizarSaldo(
                caixa.consultarSaldo()
            )

            println()
            println("Movimentação registrada com sucesso!")
            println(
                "Novo saldo: R$ ${caixa.consultarSaldo()}"
            )

        } catch (e: Exception) {

            println()
            println(
                "Não foi possível registrar a movimentação."
            )

            println(
                "Motivo: ${e.message}"
            )
        }
    }

    fun listar(): List<Movimentacao> {

        return movimentacaoDAO.listar()
    }

    fun saldo(): BigDecimal {

        return movimentacaoDAO.saldo()
    }
}