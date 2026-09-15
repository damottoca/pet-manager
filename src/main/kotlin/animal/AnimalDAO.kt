package animal

import db.ConnectionFactory
import java.sql.Connection

class AnimalDAO(
    private val conexao: Connection = ConnectionFactory.conectar()
) {

    fun cadastrar(animal: Animal) {

        val sql = """
            INSERT INTO animal (nome, especie, raca, idade, cliente_id)
            VALUES (?, ?, ?, ?, ?)
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setString(1, animal.nome)
        statement.setString(2, animal.especie)
        statement.setString(3, animal.raca)
        statement.setObject(4, animal.idade)
        statement.setInt(5, animal.clienteId)

        statement.executeUpdate()

        statement.close()
    }

    fun listar(): List<Animal> {

        val sql = "SELECT * FROM animal"

        val statement = conexao.prepareStatement(sql)
        val resultado = statement.executeQuery()

        val animais = mutableListOf<Animal>()

        while (resultado.next()) {

            val animal = Animal(
                id = resultado.getInt("id"),
                nome = resultado.getString("nome"),
                especie = resultado.getString("especie"),
                raca = resultado.getString("raca"),
                idade = resultado.getObject("idade") as Int?,
                clienteId = resultado.getInt("cliente_id")
            )

            animais.add(animal)
        }

        resultado.close()
        statement.close()

        return animais
    }

    fun atualizar(animal: Animal) {

        val sql = """
            UPDATE animal
            SET nome = ?, especie = ?, raca = ?, idade = ?, cliente_id = ?
            WHERE id = ?
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setString(1, animal.nome)
        statement.setString(2, animal.especie)
        statement.setString(3, animal.raca)
        statement.setObject(4, animal.idade)
        statement.setInt(5, animal.clienteId)
        statement.setInt(6, animal.id!!)

        statement.executeUpdate()

        statement.close()
    }

    fun excluir(id: Int) {

        val sql = "DELETE FROM animal WHERE id = ?"

        val statement = conexao.prepareStatement(sql)

        statement.setInt(1, id)

        statement.executeUpdate()

        statement.close()
    }
}