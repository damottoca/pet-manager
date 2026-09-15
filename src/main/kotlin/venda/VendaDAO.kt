package venda

import db.ConnectionFactory
import enums.TipoMovimentacao
import financeiro.Movimentacao
import java.math.BigDecimal
import java.sql.Connection
import java.sql.Timestamp
import java.time.LocalDateTime

class VendaDAO(
    private val conexao: Connection = ConnectionFactory.conectar()
) {

    fun cadastrarVendaComFinanceiro(
        venda: Venda,
        servicos: List<VendaServico>,
        pagador: String,
        recebedor: String,
        descricao: String
    ): Boolean {

        try {

            conexao.autoCommit = false

            // 1. Cadastra a venda
            val sqlVenda = """
                INSERT INTO venda (
                    data_hora,
                    cliente_id,
                    animal_id,
                    funcionario_id,
                    total
                )
                VALUES (?, ?, ?, ?, ?)
                RETURNING id
            """.trimIndent()

            val statementVenda = conexao.prepareStatement(sqlVenda)

            statementVenda.setTimestamp(
                1,
                Timestamp.valueOf(venda.dataHora)
            )

            statementVenda.setInt(2, venda.clienteId)
            statementVenda.setInt(3, venda.animalId)
            statementVenda.setInt(4, venda.funcionarioId)
            statementVenda.setBigDecimal(5, venda.total)

            val resultadoVenda = statementVenda.executeQuery()

            if (!resultadoVenda.next()) {
                throw Exception("Não foi possível criar a venda.")
            }

            val vendaId = resultadoVenda.getInt("id")

            resultadoVenda.close()
            statementVenda.close()

            // 2. Adiciona os serviços da venda
            val sqlServico = """
                INSERT INTO venda_servico (
                    venda_id,
                    servico_id,
                    quantidade
                )
                VALUES (?, ?, ?)
            """.trimIndent()

            for (item in servicos) {

                val statementServico =
                    conexao.prepareStatement(sqlServico)

                statementServico.setInt(1, vendaId)
                statementServico.setInt(2, item.servicoId)
                statementServico.setInt(3, item.quantidade)

                statementServico.executeUpdate()

                statementServico.close()
            }

            // 3. Registra a movimentação financeira
            val sqlMovimentacao = """
                INSERT INTO movimentacao_financeira (
                    valor,
                    tipo,
                    pagador,
                    recebedor,
                    data_hora,
                    descricao,
                    responsavel_id
                )
                VALUES (?, ?, ?, ?, ?, ?, ?)
            """.trimIndent()

            val statementMovimentacao =
                conexao.prepareStatement(sqlMovimentacao)

            statementMovimentacao.setBigDecimal(
                1,
                venda.total
            )

            statementMovimentacao.setString(
                2,
                TipoMovimentacao.ENTRADA.name
            )

            statementMovimentacao.setString(
                3,
                pagador
            )

            statementMovimentacao.setString(
                4,
                recebedor
            )

            statementMovimentacao.setTimestamp(
                5,
                Timestamp.valueOf(LocalDateTime.now())
            )

            statementMovimentacao.setString(
                6,
                descricao
            )

            statementMovimentacao.setInt(
                7,
                venda.funcionarioId
            )

            statementMovimentacao.executeUpdate()

            statementMovimentacao.close()

            // 4. Busca o saldo atual do caixa
            val sqlSaldo = """
                SELECT saldo
                FROM caixa
                WHERE id = 1
                FOR UPDATE
            """.trimIndent()

            val statementSaldo =
                conexao.prepareStatement(sqlSaldo)

            val resultadoSaldo =
                statementSaldo.executeQuery()

            if (!resultadoSaldo.next()) {
                throw Exception("Caixa não encontrado.")
            }

            val saldoAtual =
                resultadoSaldo.getBigDecimal("saldo")

            resultadoSaldo.close()
            statementSaldo.close()

            // 5. Calcula o novo saldo
            val novoSaldo =
                saldoAtual.add(venda.total)

            // 6. Atualiza o caixa
            val sqlAtualizarCaixa = """
                UPDATE caixa
                SET saldo = ?
                WHERE id = 1
            """.trimIndent()

            val statementCaixa =
                conexao.prepareStatement(sqlAtualizarCaixa)

            statementCaixa.setBigDecimal(
                1,
                novoSaldo
            )

            statementCaixa.executeUpdate()

            statementCaixa.close()

            // 7. Confirma tudo
            conexao.commit()

            println()
            println("Venda realizada com sucesso!")
            println("Venda número: $vendaId")
            println("Total: R$ ${venda.total}")
            println("Financeiro atualizado!")
            println("Novo saldo do caixa: R$ $novoSaldo")

            return true

        } catch (e: Exception) {

            // Se qualquer etapa falhar,
            // desfaz todas as alterações.
            conexao.rollback()

            println()
            println("Erro ao realizar a venda.")
            println("A operação foi desfeita.")
            println("Motivo: ${e.message}")

            return false

        } finally {

            conexao.autoCommit = true
        }
    }

    fun listar(): List<Venda> {

        val sql = """
            SELECT
                id,
                data_hora,
                cliente_id,
                animal_id,
                funcionario_id,
                total
            FROM venda
            ORDER BY data_hora DESC
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)
        val resultado = statement.executeQuery()

        val vendas = mutableListOf<Venda>()

        while (resultado.next()) {

            vendas.add(
                Venda(
                    id = resultado.getInt("id"),
                    dataHora = resultado
                        .getTimestamp("data_hora")
                        .toLocalDateTime(),
                    clienteId = resultado.getInt("cliente_id"),
                    animalId = resultado.getInt("animal_id"),
                    funcionarioId = resultado.getInt("funcionario_id"),
                    total = resultado.getBigDecimal("total")
                )
            )
        }

        resultado.close()
        statement.close()

        return vendas
    }

    fun listarServicos(vendaId: Int): List<VendaServico> {

        val sql = """
            SELECT
                venda_id,
                servico_id,
                quantidade
            FROM venda_servico
            WHERE venda_id = ?
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setInt(1, vendaId)

        val resultado = statement.executeQuery()

        val servicos = mutableListOf<VendaServico>()

        while (resultado.next()) {

            servicos.add(
                VendaServico(
                    vendaId = resultado.getInt("venda_id"),
                    servicoId = resultado.getInt("servico_id"),
                    quantidade = resultado.getInt("quantidade")
                )
            )
        }

        resultado.close()
        statement.close()

        return servicos
    }
}