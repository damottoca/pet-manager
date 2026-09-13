package funcionario

import db.ConnectionFactory
import java.sql.Connection

class FuncionarioDAO(
    private val conexao: Connection = ConnectionFactory.conectar()
) {

    fun cadastrar(funcionario: Funcionario) {

        val sql = """
            INSERT INTO funcionario (nome, cargo, telefone)
            VALUES (?, ?, ?)
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setString(1, funcionario.nome)
        statement.setString(2, funcionario.cargo)
        statement.setString(3, funcionario.telefone)

        statement.executeUpdate()

        statement.close()
    }
}