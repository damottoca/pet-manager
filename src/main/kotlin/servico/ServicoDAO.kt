package servico

import db.ConnectionFactory
import java.sql.Connection

class ServicoDAO(
    private val conexao: Connection = ConnectionFactory.conectar()
) {

    fun cadastrar(servico: Servico) {

        val sql = """
            INSERT INTO servico (nome, descricao, preco)
            VALUES (?, ?, ?)
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setString(1, servico.nome)
        statement.setString(2, servico.descricao)
        statement.setBigDecimal(3, servico.preco)

        statement.executeUpdate()

        statement.close()
    }
}
