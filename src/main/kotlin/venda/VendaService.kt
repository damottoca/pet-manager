package venda

import servico.ServicoService
import java.math.BigDecimal

class VendaService(
    private val vendaDAO: VendaDAO,
    private val servicoService: ServicoService
) {

    fun cadastrar(
        venda: Venda,
        servicos: List<VendaServico>,
        pagador: String,
        recebedor: String,
        descricao: String
    ): Boolean {

        if (venda.clienteId <= 0) {
            println("Cliente inválido.")
            return false
        }

        if (venda.animalId <= 0) {
            println("Animal inválido.")
            return false
        }

        if (venda.funcionarioId <= 0) {
            println("Funcionário responsável inválido.")
            return false
        }

        if (servicos.isEmpty()) {
            println("A venda precisa ter pelo menos um serviço.")
            return false
        }

        if (pagador.isBlank()) {
            println("O pagador é obrigatório.")
            return false
        }

        if (recebedor.isBlank()) {
            println("O recebedor é obrigatório.")
            return false
        }

        if (descricao.isBlank()) {
            println("A descrição é obrigatória.")
            return false
        }

        var total = BigDecimal.ZERO

        val servicosCadastrados = servicoService.listar()

        for (item in servicos) {

            if (item.quantidade <= 0) {
                println("A quantidade deve ser maior que zero.")
                return false
            }

            val servico = servicosCadastrados.find {
                it.id == item.servicoId
            }

            if (servico == null) {
                println("Serviço ${item.servicoId} não encontrado.")
                return false
            }

            val subtotal = servico.preco.multiply(
                BigDecimal.valueOf(item.quantidade.toLong())
            )

            total = total.add(subtotal)
        }

        if (total <= BigDecimal.ZERO) {
            println("O total da venda deve ser maior que zero.")
            return false
        }

        val vendaComTotal = venda.copy(
            total = total
        )

        return vendaDAO.cadastrarVendaComFinanceiro(
            venda = vendaComTotal,
            servicos = servicos,
            pagador = pagador,
            recebedor = recebedor,
            descricao = descricao
        )
    }

    fun listar(): List<Venda> {
        return vendaDAO.listar()
    }

    fun listarServicos(vendaId: Int): List<VendaServico> {
        return vendaDAO.listarServicos(vendaId)
    }
}