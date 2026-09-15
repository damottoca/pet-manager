package financeiro

import enums.TipoMovimentacao
import funcionario.FuncionarioService
import java.time.format.DateTimeFormatter

class MovimentacaoMenu(
    private val movimentacaoService: MovimentacaoService,
    private val funcionarioService: FuncionarioService,
    private val auditoriaService: AuditoriaService
) {

    fun exibir() {

        var continuar = true

        while (continuar) {

            println()
            println("====== FINANCEIRO ======")
            println("1 - Ver saldo")
            println("2 - Registrar entrada")
            println("3 - Registrar saída")
            println("4 - Listar movimentações")
            println("5 - Auditoria financeira")
            println("0 - Voltar")
            println()

            print("Escolha uma opção: ")

            when (readln()) {

                "1" -> saldo()

                "2" -> registrar(
                    TipoMovimentacao.ENTRADA
                )

                "3" -> registrar(
                    TipoMovimentacao.SAIDA
                )

                "4" -> listar()

                "5" -> auditoriaService.auditar()

                "0" -> {
                    println("Voltando...")
                    continuar = false
                }

                else -> println("Opção inválida!")
            }
        }
    }

    private fun saldo() {

        println()
        println("====== SALDO DO CAIXA ======")

        println(
            "Saldo atual: R$ " +
                    movimentacaoService.saldo()
        )
    }

    private fun registrar(
        tipo: TipoMovimentacao
    ) {

        println()

        if (tipo == TipoMovimentacao.ENTRADA) {
            println("====== REGISTRAR ENTRADA ======")
        } else {
            println("====== REGISTRAR SAÍDA ======")
        }

        print("Valor: ")

        val valor = readln()
            .replace(",", ".")
            .toBigDecimalOrNull()

        if (valor == null) {
            println("Valor inválido.")
            return
        }

        print("Pagador: ")
        val pagador = readln()

        print("Recebedor: ")
        val recebedor = readln()

        print("Descrição: ")
        val descricao = readln()

        println()
        println("====== FUNCIONÁRIOS ======")

        val funcionarios = funcionarioService.listar()

        if (funcionarios.isEmpty()) {
            println("Nenhum funcionário cadastrado.")
            return
        }

        for (funcionario in funcionarios) {

            println(
                "${funcionario.id} - " +
                        "${funcionario.nome} - " +
                        "${funcionario.cargo}"
            )
        }

        println()

        print(
            "ID do funcionário responsável: "
        )

        val responsavelId = readln().toIntOrNull()

        if (responsavelId == null) {
            println("Responsável inválido.")
            return
        }

        if (
            funcionarios.none {
                it.id == responsavelId
            }
        ) {
            println(
                "Funcionário responsável não encontrado."
            )

            return
        }

        val movimentacao = Movimentacao(
            valor = valor,
            tipo = tipo,
            pagador = pagador,
            recebedor = recebedor,
            descricao = descricao,
            responsavelId = responsavelId
        )

        movimentacaoService.registrar(
            movimentacao
        )
    }

    private fun listar() {

        val movimentacoes =
            movimentacaoService.listar()

        println()
        println("====== MOVIMENTAÇÕES ======")

        if (movimentacoes.isEmpty()) {

            println(
                "Nenhuma movimentação cadastrada."
            )

            return
        }

        val formatter =
            DateTimeFormatter.ofPattern(
                "dd/MM/yyyy HH:mm"
            )

        for (movimentacao in movimentacoes) {

            println()
            println("ID: ${movimentacao.id}")
            println("Tipo: ${movimentacao.tipo}")
            println("Valor: R$ ${movimentacao.valor}")
            println("Pagador: ${movimentacao.pagador}")
            println("Recebedor: ${movimentacao.recebedor}")

            println(
                "Data/Hora: " +
                        movimentacao.dataHora.format(formatter)
            )

            println(
                "Descrição: " +
                        movimentacao.descricao
            )

            println(
                "Responsável: " +
                        movimentacao.responsavelId
            )
        }
    }
}