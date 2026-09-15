package agendamento

class AgendamentoService(
    private val agendamentoDAO: AgendamentoDAO
) {

    fun cadastrar(agendamento: Agendamento) {

        if (agendamento.animalId <= 0) {
            println("ID do animal inválido.")
            return
        }

        if (agendamento.servicoId <= 0) {
            println("ID do serviço inválido.")
            return
        }

        if (agendamento.funcionarioId <= 0) {
            println("ID do funcionário inválido.")
            return
        }

        agendamentoDAO.cadastrar(agendamento)

        println("Agendamento cadastrado com sucesso!")
    }

    fun listar(): List<Agendamento> {
        return agendamentoDAO.listar()
    }

    fun atualizar(agendamento: Agendamento) {

        if (agendamento.id == null) {
            println("ID do agendamento inválido.")
            return
        }

        if (agendamento.animalId <= 0) {
            println("ID do animal inválido.")
            return
        }

        if (agendamento.servicoId <= 0) {
            println("ID do serviço inválido.")
            return
        }

        if (agendamento.funcionarioId <= 0) {
            println("ID do funcionário inválido.")
            return
        }

        agendamentoDAO.atualizar(agendamento)

        println("Agendamento atualizado com sucesso!")
    }

    fun excluir(id: Int) {

        if (id <= 0) {
            println("ID do agendamento inválido.")
            return
        }

        agendamentoDAO.excluir(id)

        println("Agendamento excluído com sucesso!")
    }
}