package financeiro

import db.ConnectionFactory
import enums.TipoMovimentacao
import java.sql.Connection
import java.sql.Timestamp
import java.math.BigDecimal

class MovimentacaoDAO(
    private val conexao: Connection = ConnectionFactory.conectar()
) {

    fun cadastrar(movimentacao: Movimentacao) {

        val sql = """
            INSERT INTO movimentacao_financeira
            (valor, tipo, pagador, recebedor, data_hora, descricao, responsavel_id)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setBigDecimal(1, movimentacao.valor)
        statement.setString(2, movimentacao.tipo.name)
        statement.setString(3, movimentacao.pagador)
        statement.setString(4, movimentacao.recebedor)
        statement.setTimestamp(5, Timestamp.valueOf(movimentacao.dataHora))
        statement.setString(6, movimentacao.descricao)
        statement.setInt(7, movimentacao.responsavelId)

        statement.executeUpdate()
        statement.close()
    }

    fun listar(): List<Movimentacao> {

        val sql = """
            SELECT *
            FROM movimentacao_financeira
            ORDER BY data_hora DESC
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)
        val resultado = statement.executeQuery()

        val movimentacoes = mutableListOf<Movimentacao>()

        while (resultado.next()) {

            movimentacoes.add(
                Movimentacao(
                    id = resultado.getInt("id"),
                    valor = resultado.getBigDecimal("valor"),
                    tipo = TipoMovimentacao.valueOf(
                        resultado.getString("tipo")
                    ),
                    pagador = resultado.getString("pagador"),
                    recebedor = resultado.getString("recebedor"),
                    dataHora = resultado
                        .getTimestamp("data_hora")
                        .toLocalDateTime(),
                    descricao = resultado.getString("descricao"),
                    responsavelId = resultado.getInt("responsavel_id")
                )
            )
        }

        resultado.close()
        statement.close()

        return movimentacoes
    }

    fun saldo(): BigDecimal {

        val sql = """
            SELECT saldo
            FROM caixa
            WHERE id = 1
            FOR UPDATE
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)
        val resultado = statement.executeQuery()

        resultado.next()

        val saldo = resultado.getBigDecimal("saldo")

        resultado.close()
        statement.close()

        return saldo
    }

    fun atualizarSaldo(novoSaldo: BigDecimal) {

        val sql = """
            UPDATE caixa
            SET saldo = ?
            WHERE id = 1
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setBigDecimal(1, novoSaldo)

        statement.executeUpdate()

        statement.close()
    }
}