package setor

import db.ConnectionFactory
import java.sql.Connection

class SetorDAO(
    private val conexao: Connection = ConnectionFactory.conectar()
) {

    fun listar(): List<Setor> {

        val sql = "SELECT * FROM setor ORDER BY id"

        val statement = conexao.prepareStatement(sql)
        val resultado = statement.executeQuery()

        val setores = mutableListOf<Setor>()

        while (resultado.next()) {

            val setor = Setor(
                id = resultado.getInt("id"),
                nome = resultado.getString("nome")
            )

            setores.add(setor)
        }

        resultado.close()
        statement.close()

        return setores
    }
}