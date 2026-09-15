package agendamento

import animal.AnimalService
import funcionario.FuncionarioService
import servico.ServicoService
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AgendamentoMenu(
    private val agendamentoService: AgendamentoService,
    private val animalService: AnimalService,
    private val servicoService: ServicoService,
    private val funcionarioService: FuncionarioService
) {

    fun exibir() {

        var continuar = true

        while (continuar) {

            println()
            println("====== AGENDAMENTOS ======")
            println("1 - Listar agendamentos")
            println("2 - Cadastrar agendamento")
            println("3 - Atualizar agendamento")
            println("4 - Excluir agendamento")
            println("0 - Voltar")
            println()

            print("Escolha uma opção: ")

            when (readln()) {

                "1" -> listar()

                "2" -> cadastrar()

                "3" -> atualizar()

                "4" -> excluir()

                "0" -> {
                    println("Voltando...")
                    continuar = false
                }

                else -> println("Opção inválida!")
            }
        }
    }

    private fun listar() {

        val agendamentos = agendamentoService.listar()

        println()
        println("====== AGENDAMENTOS CADASTRADOS ======")

        if (agendamentos.isEmpty()) {
            println("Nenhum agendamento cadastrado.")
            return
        }

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

        for (agendamento in agendamentos) {

            println(
                "${agendamento.id} - " +
                        "${agendamento.dataHora.format(formatter)} - " +
                        "Animal: ${agendamento.animalNome} - " +
                        "Serviço: ${agendamento.servicoNome} - " +
                        "Funcionário: ${agendamento.funcionarioNome} - " +
                        "${agendamento.observacao}"
            )
        }
    }

    private fun cadastrar() {

        println()
        println("====== CADASTRAR AGENDAMENTO ======")

        val dataHora = lerDataHora() ?: return

        val animalId = escolherAnimal() ?: return

        val servicoId = escolherServico() ?: return

        val funcionarioId = escolherFuncionario() ?: return

        print("Observação: ")
        val observacao = readln()

        val agendamento = Agendamento(
            dataHora = dataHora,
            animalId = animalId,
            servicoId = servicoId,
            funcionarioId = funcionarioId,
            observacao = observacao
        )

        agendamentoService.cadastrar(agendamento)
    }

    private fun atualizar() {

        println()
        println("====== ATUALIZAR AGENDAMENTO ======")

        val agendamentos = agendamentoService.listar()

        if (agendamentos.isEmpty()) {
            println("Nenhum agendamento cadastrado.")
            return
        }

        listar()

        println()
        print("ID do agendamento: ")

        val id = readln().toIntOrNull()

        if (id == null) {
            println("ID inválido.")
            return
        }

        if (agendamentos.none { it.id == id }) {
            println("Agendamento não encontrado.")
            return
        }

        val dataHora = lerDataHora() ?: return

        val animalId = escolherAnimal() ?: return

        val servicoId = escolherServico() ?: return

        val funcionarioId = escolherFuncionario() ?: return

        print("Nova observação: ")
        val observacao = readln()

        val agendamento = Agendamento(
            id = id,
            dataHora = dataHora,
            animalId = animalId,
            servicoId = servicoId,
            funcionarioId = funcionarioId,
            observacao = observacao
        )

        agendamentoService.atualizar(agendamento)
    }

    private fun excluir() {

        println()
        println("====== EXCLUIR AGENDAMENTO ======")

        val agendamentos = agendamentoService.listar()

        if (agendamentos.isEmpty()) {
            println("Nenhum agendamento cadastrado.")
            return
        }

        listar()

        println()
        print("ID do agendamento: ")

        val id = readln().toIntOrNull()

        if (id == null) {
            println("ID inválido.")
            return
        }

        if (agendamentos.none { it.id == id }) {
            println("Agendamento não encontrado.")
            return
        }

        print("Tem certeza que deseja excluir este agendamento? (s/n): ")

        val confirmacao = readln()

        if (confirmacao.lowercase() == "s") {
            agendamentoService.excluir(id)
        } else {
            println("Exclusão cancelada.")
        }
    }

    private fun lerDataHora(): LocalDateTime? {

        print("Data e hora (dd/MM/yyyy HH:mm): ")

        val dataHoraTexto = readln()

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

        return try {
            LocalDateTime.parse(dataHoraTexto, formatter)
        } catch (e: Exception) {
            println("Data ou hora inválida.")
            null
        }
    }

    private fun escolherAnimal(): Int? {

        val animais = animalService.listar()

        if (animais.isEmpty()) {
            println("Nenhum animal cadastrado.")
            return null
        }

        println()
        println("====== ESCOLHER ANIMAL ======")

        for (animal in animais) {
            println(
                "${animal.id} - ${animal.nome} (${animal.especie})"
            )
        }

        print("ID do animal: ")

        val id = readln().toIntOrNull()

        if (id == null) {
            println("ID do animal inválido.")
            return null
        }

        if (animais.none { it.id == id }) {
            println("Animal não encontrado.")
            return null
        }

        return id
    }

    private fun escolherServico(): Int? {

        val servicos = servicoService.listar()

        if (servicos.isEmpty()) {
            println("Nenhum serviço cadastrado.")
            return null
        }

        println()
        println("====== ESCOLHER SERVIÇO ======")

        for (servico in servicos) {
            println(
                "${servico.id} - ${servico.nome} - R$ ${servico.preco}"
            )
        }

        print("ID do serviço: ")

        val id = readln().toIntOrNull()

        if (id == null) {
            println("ID do serviço inválido.")
            return null
        }

        if (servicos.none { it.id == id }) {
            println("Serviço não encontrado.")
            return null
        }

        return id
    }

    private fun escolherFuncionario(): Int? {

        val funcionarios = funcionarioService.listar()

        if (funcionarios.isEmpty()) {
            println("Nenhum funcionário cadastrado.")
            return null
        }

        println()
        println("====== ESCOLHER FUNCIONÁRIO ======")

        for (funcionario in funcionarios) {
            println(
                "${funcionario.id} - ${funcionario.nome} (${funcionario.cargo})"
            )
        }

        print("ID do funcionário: ")

        val id = readln().toIntOrNull()

        if (id == null) {
            println("ID do funcionário inválido.")
            return null
        }

        if (funcionarios.none { it.id == id }) {
            println("Funcionário não encontrado.")
            return null
        }

        return id
    }
}