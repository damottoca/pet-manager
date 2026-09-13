package cliente

import db.ConnectionFactory
import java.sql.Connection

class ClienteDAO(
    private val conexao: Connection = ConnectionFactory.conectar()
) {

    fun cadastrar(cliente: Cliente) {

        val sql = """
            INSERT INTO cliente (nome, telefone, email)
            VALUES (?, ?, ?)
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setString(1, cliente.nome)
        statement.setString(2, cliente.telefone)
        statement.setString(3, cliente.email)

        statement.executeUpdate()

        statement.close()
    }

    fun listar(): List<Cliente> {

        val sql = "SELECT * FROM cliente"

        val statement = conexao.prepareStatement(sql)
        val resultado = statement.executeQuery()

        val clientes = mutableListOf<Cliente>()

        while (resultado.next()) {

            val cliente = Cliente(
                id = resultado.getInt("id"),
                nome = resultado.getString("nome"),
                telefone = resultado.getString("telefone"),
                email = resultado.getString("email")
            )

            clientes.add(cliente)
        }

        resultado.close()
        statement.close()

        return clientes
    }

    fun atualizar(cliente: Cliente) {

        val sql = """
            UPDATE cliente
            SET nome = ?, telefone = ?, email = ?
            WHERE id = ?
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setString(1, cliente.nome)
        statement.setString(2, cliente.telefone)
        statement.setString(3, cliente.email)
        statement.setInt(4, cliente.id!!)

        statement.executeUpdate()

        statement.close()
    }

    fun excluir(id: Int) {

        val sql = "DELETE FROM cliente WHERE id = ?"

        val statement = conexao.prepareStatement(sql)

        statement.setInt(1, id)

        statement.executeUpdate()

        statement.close()
    }
}