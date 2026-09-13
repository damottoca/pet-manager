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
}