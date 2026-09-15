package funcionario

import db.ConnectionFactory
import java.sql.Connection

class FuncionarioDAO(
    private val conexao: Connection = ConnectionFactory.conectar()
) {

    fun cadastrar(funcionario: Funcionario) {

        val sql = """
            INSERT INTO funcionario (nome, cargo, telefone, setor_id)
            VALUES (?, ?, ?, ?)
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setString(1, funcionario.nome)
        statement.setString(2, funcionario.cargo)
        statement.setString(3, funcionario.telefone)
        statement.setInt(4, funcionario.setorId)

        statement.executeUpdate()

        statement.close()
    }

    fun listar(): List<Funcionario> {

        val sql = """
            SELECT *
            FROM funcionario
            ORDER BY id
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)
        val resultado = statement.executeQuery()

        val funcionarios = mutableListOf<Funcionario>()

        while (resultado.next()) {

            val funcionario = Funcionario(
                id = resultado.getInt("id"),
                nome = resultado.getString("nome"),
                cargo = resultado.getString("cargo"),
                telefone = resultado.getString("telefone"),
                setorId = resultado.getInt("setor_id")
            )

            funcionarios.add(funcionario)
        }

        resultado.close()
        statement.close()

        return funcionarios
    }

    fun atualizar(funcionario: Funcionario) {

        val sql = """
            UPDATE funcionario
            SET nome = ?,
                cargo = ?,
                telefone = ?,
                setor_id = ?
            WHERE id = ?
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setString(1, funcionario.nome)
        statement.setString(2, funcionario.cargo)
        statement.setString(3, funcionario.telefone)
        statement.setInt(4, funcionario.setorId)
        statement.setInt(5, funcionario.id!!)

        statement.executeUpdate()

        statement.close()
    }

    fun excluir(id: Int) {

        val sql = "DELETE FROM funcionario WHERE id = ?"

        val statement = conexao.prepareStatement(sql)

        statement.setInt(1, id)

        statement.executeUpdate()

        statement.close()
    }
}