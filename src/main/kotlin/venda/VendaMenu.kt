package venda

import animal.AnimalService
import cliente.ClienteService
import funcionario.FuncionarioService
import servico.ServicoService
import java.math.BigDecimal
import java.time.format.DateTimeFormatter

class VendaMenu(
    private val vendaService: VendaService,
    private val clienteService: ClienteService,
    private val animalService: AnimalService,
    private val servicoService: ServicoService,
    private val funcionarioService: FuncionarioService
) {

    fun exibir() {

        var continuar = true

        while (continuar) {

            println()
            println("====== VENDAS ======")
            println("1 - Listar vendas")
            println("2 - Registrar venda")
            println("0 - Voltar")
            println()

            print("Escolha uma opção: ")

            when (readln()) {

                "1" -> listar()

                "2" -> cadastrar()

                "0" -> {
                    println("Voltando...")
                    continuar = false
                }

                else -> println("Opção inválida!")
            }
        }
    }

    private fun cadastrar() {

        println()
        println("====== REGISTRAR VENDA ======")

        // ==============================
        // CLIENTE
        // ==============================

        val clientes = clienteService.listar()

        if (clientes.isEmpty()) {
            println("Nenhum cliente cadastrado.")
            return
        }

        println()
        println("====== CLIENTES ======")

        for (cliente in clientes) {
            println("${cliente.id} - ${cliente.nome}")
        }

        print("ID do cliente: ")

        val clienteId = readln().toIntOrNull()

        if (
            clienteId == null ||
            clientes.none { it.id == clienteId }
        ) {
            println("Cliente inválido.")
            return
        }

        val cliente = clientes.first {
            it.id == clienteId
        }


        // ==============================
        // ANIMAL
        // ==============================

        val animais = animalService.listar()
            .filter { it.clienteId == clienteId }

        if (animais.isEmpty()) {
            println("Esse cliente não possui animais cadastrados.")
            return
        }

        println()
        println("====== ANIMAIS DO CLIENTE ======")

        for (animal in animais) {

            println(
                "${animal.id} - ${animal.nome} (${animal.especie})"
            )
        }

        print("ID do animal: ")

        val animalId = readln().toIntOrNull()

        if (
            animalId == null ||
            animais.none { it.id == animalId }
        ) {
            println("Animal inválido.")
            return
        }


        // ==============================
        // SERVIÇOS
        // ==============================

        val servicos = servicoService.listar()

        if (servicos.isEmpty()) {
            println("Nenhum serviço cadastrado.")
            return
        }

        val itens = mutableListOf<VendaServico>()

        while (true) {

            println()
            println("====== SERVIÇOS ======")

            for (servico in servicos) {

                println(
                    "${servico.id} - ${servico.nome} - R$ ${servico.preco}"
                )
            }

            println("0 - Finalizar escolha dos serviços")
            println()

            print("ID do serviço: ")

            val servicoId = readln().toIntOrNull()

            if (servicoId == 0) {
                break
            }

            if (
                servicoId == null ||
                servicos.none { it.id == servicoId }
            ) {
                println("Serviço inválido.")
                continue
            }

            print("Quantidade: ")

            val quantidade = readln().toIntOrNull()

            if (
                quantidade == null ||
                quantidade <= 0
            ) {
                println("Quantidade inválida.")
                continue
            }

            if (
                itens.any {
                    it.servicoId == servicoId
                }
            ) {
                println("Esse serviço já foi adicionado.")
                continue
            }

            itens.add(
                VendaServico(
                    vendaId = 0,
                    servicoId = servicoId,
                    quantidade = quantidade
                )
            )

            println("Serviço adicionado!")
        }

        if (itens.isEmpty()) {

            println(
                "A venda precisa ter pelo menos um serviço."
            )

            return
        }


        // ==============================
        // FUNCIONÁRIO RESPONSÁVEL
        // ==============================

        val funcionarios = funcionarioService.listar()

        if (funcionarios.isEmpty()) {

            println(
                "Nenhum funcionário cadastrado."
            )

            return
        }

        println()
        println("====== FUNCIONÁRIO RESPONSÁVEL ======")

        for (funcionario in funcionarios) {

            println(
                "${funcionario.id} - " +
                        "${funcionario.nome} - " +
                        "${funcionario.cargo}"
            )
        }

        println()

        print(
            "ID do funcionário responsável: "
        )

        val funcionarioId = readln().toIntOrNull()

        if (
            funcionarioId == null ||
            funcionarios.none {
                it.id == funcionarioId
            }
        ) {

            println(
                "Funcionário responsável inválido."
            )

            return
        }

        val funcionario = funcionarios.first {
            it.id == funcionarioId
        }


        // ==============================
        // DADOS DA VENDA
        // ==============================

        val venda = Venda(
            clienteId = clienteId,
            animalId = animalId,
            funcionarioId = funcionarioId,
            total = BigDecimal.ZERO
        )


        // ==============================
        // DADOS FINANCEIROS
        // ==============================

        val pagador = cliente.nome

        val recebedor = "PetManager"

        val nomesServicos = itens.mapNotNull { item ->

            servicos.find {
                it.id == item.servicoId
            }?.nome
        }

        val descricao =
            "Venda de serviços: " +
                    nomesServicos.joinToString(", ")


        // ==============================
        // CONFIRMAÇÃO DA VENDA
        // ==============================

        println()
        println("================================")
        println("      CONFIRMAÇÃO DA VENDA")
        println("================================")

        println()
        println("Cliente: ${cliente.nome}")

        println("Animal ID: $animalId")

        println(
            "Serviços: " +
                    nomesServicos.joinToString(", ")
        )

        println(
            "Funcionário responsável: " +
                    "${funcionario.nome}"
        )

        println(
            "Cargo: " +
                    "${funcionario.cargo}"
        )

        println(
            "Pagador: " +
                    pagador
        )

        println(
            "Recebedor: " +
                    recebedor
        )

        println(
            "Descrição: " +
                    descricao
        )

        println()

        print(
            "Confirmar venda? (s/n): "
        )

        val confirmacao = readln()

        if (
            confirmacao.lowercase() != "s"
        ) {

            println()
            println("Venda cancelada.")

            return
        }


        // ==============================
        // REALIZA A VENDA
        // ==============================

        vendaService.cadastrar(
            venda = venda,
            servicos = itens,
            pagador = pagador,
            recebedor = recebedor,
            descricao = descricao
        )
    }


    // ==============================
    // LISTAR VENDAS
    // ==============================

    private fun listar() {

        val vendas = vendaService.listar()

        println()
        println("====== VENDAS REALIZADAS ======")

        if (vendas.isEmpty()) {

            println(
                "Nenhuma venda cadastrada."
            )

            return
        }

        val formatter =
            DateTimeFormatter.ofPattern(
                "dd/MM/yyyy HH:mm"
            )

        for (venda in vendas) {

            println()

            println(
                "Venda #${venda.id}"
            )

            println(
                "Data: " +
                        venda.dataHora.format(formatter)
            )

            println(
                "Cliente ID: " +
                        venda.clienteId
            )

            println(
                "Animal ID: " +
                        venda.animalId
            )

            println(
                "Funcionário ID: " +
                        venda.funcionarioId
            )

            println(
                "Total: R$ " +
                        venda.total
            )

            val servicos =
                vendaService.listarServicos(
                    venda.id!!
                )

            println("Serviços:")

            for (item in servicos) {

                val servico =
                    servicoService
                        .listar()
                        .find {
                            it.id == item.servicoId
                        }

                println(
                    "- " +
                            "${servico?.nome ?: "Serviço não encontrado"} " +
                            "x${item.quantidade}"
                )
            }
        }
    }
}