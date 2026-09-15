package agendamento

import db.ConnectionFactory
import java.sql.Connection
import java.sql.Timestamp

class AgendamentoDAO(
    private val conexao: Connection = ConnectionFactory.conectar()
) {

    fun cadastrar(agendamento: Agendamento) {

        val sql = """
            INSERT INTO agendamento (
                data_hora,
                animal_id,
                servico_id,
                funcionario_id,
                observacao
            )
            VALUES (?, ?, ?, ?, ?)
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setTimestamp(
            1,
            Timestamp.valueOf(agendamento.dataHora)
        )

        statement.setInt(2, agendamento.animalId)
        statement.setInt(3, agendamento.servicoId)
        statement.setInt(4, agendamento.funcionarioId)
        statement.setString(5, agendamento.observacao)

        statement.executeUpdate()

        statement.close()
    }

    fun listar(): List<Agendamento> {

        val sql = """
            SELECT 
                agendamento.id,
                agendamento.data_hora,
                agendamento.animal_id,
                animal.nome AS animal_nome,
                agendamento.servico_id,
                servico.nome AS servico_nome,
                agendamento.funcionario_id,
                funcionario.nome AS funcionario_nome,
                agendamento.observacao
            FROM agendamento
            INNER JOIN animal
                ON agendamento.animal_id = animal.id
            INNER JOIN servico
                ON agendamento.servico_id = servico.id
            INNER JOIN funcionario
                ON agendamento.funcionario_id = funcionario.id
            ORDER BY agendamento.data_hora
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)
        val resultado = statement.executeQuery()

        val agendamentos = mutableListOf<Agendamento>()

        while (resultado.next()) {

            val agendamento = Agendamento(
                id = resultado.getInt("id"),
                dataHora = resultado
                    .getTimestamp("data_hora")
                    .toLocalDateTime(),
                animalId = resultado.getInt("animal_id"),
                animalNome = resultado.getString("animal_nome"),
                servicoId = resultado.getInt("servico_id"),
                servicoNome = resultado.getString("servico_nome"),
                funcionarioId = resultado.getInt("funcionario_id"),
                funcionarioNome = resultado.getString("funcionario_nome"),
                observacao = resultado.getString("observacao")
            )

            agendamentos.add(agendamento)
        }

        resultado.close()
        statement.close()

        return agendamentos
    }

    fun atualizar(agendamento: Agendamento) {

        val sql = """
            UPDATE agendamento
            SET data_hora = ?,
                animal_id = ?,
                servico_id = ?,
                funcionario_id = ?,
                observacao = ?
            WHERE id = ?
        """.trimIndent()

        val statement = conexao.prepareStatement(sql)

        statement.setTimestamp(
            1,
            Timestamp.valueOf(agendamento.dataHora)
        )

        statement.setInt(2, agendamento.animalId)
        statement.setInt(3, agendamento.servicoId)
        statement.setInt(4, agendamento.funcionarioId)
        statement.setString(5, agendamento.observacao)
        statement.setInt(6, agendamento.id!!)

        statement.executeUpdate()

        statement.close()
    }

    fun excluir(id: Int) {

        val sql = "DELETE FROM agendamento WHERE id = ?"

        val statement = conexao.prepareStatement(sql)

        statement.setInt(1, id)

        statement.executeUpdate()

        statement.close()
    }
}