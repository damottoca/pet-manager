package servico

class ServicoService(
    private val servicoDAO: ServicoDAO
) {

    fun cadastrar(servico: Servico) {

        if (servico.nome.isBlank()) {
            println("O nome do serviço não pode ficar vazio.")
            return
        }

        if (servico.preco < java.math.BigDecimal.ZERO) {
            println("O preço não pode ser negativo.")
            return
        }

        servicoDAO.cadastrar(servico)

        println("Serviço cadastrado com sucesso!")
    }

    fun listar(): List<Servico> {
        return servicoDAO.listar()
    }

    fun atualizar(servico: Servico) {

        if (servico.id == null) {
            println("ID do serviço inválido.")
            return
        }

        if (servico.nome.isBlank()) {
            println("O nome do serviço não pode ficar vazio.")
            return
        }

        if (servico.preco < java.math.BigDecimal.ZERO) {
            println("O preço não pode ser negativo.")
            return
        }

        servicoDAO.atualizar(servico)

        println("Serviço atualizado com sucesso!")
    }

    fun excluir(id: Int) {

        if (id <= 0) {
            println("ID do serviço inválido.")
            return
        }

        servicoDAO.excluir(id)

        println("Serviço excluído com sucesso!")
    }
}