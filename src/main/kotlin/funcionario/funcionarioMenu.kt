package funcionario

import setor.SetorDAO
import pessoa.Pessoa

class FuncionarioMenu(
    private val funcionarioService: FuncionarioService
) {

    private val setorDAO = SetorDAO()

    fun exibir() {

        var continuar = true

        while (continuar) {

            println()
            println("====== FUNCIONÁRIOS ======")
            println("1 - Listar funcionários")
            println("2 - Cadastrar funcionário")
            println("3 - Atualizar funcionário")
            println("4 - Excluir funcionário")
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

        val funcionarios = funcionarioService.listar()

        println()
        println("====== FUNCIONÁRIOS CADASTRADOS ======")

        if (funcionarios.isEmpty()) {
            println("Nenhum funcionário cadastrado.")
            return
        }

        val setores = setorDAO.listar()

        for (funcionario in funcionarios) {

            val pessoa: Pessoa = funcionario

            val setor = setores.find {
                it.id == funcionario.setorId
            }

            println(
                "${pessoa.tipoPessoa()} - " +
                        "${funcionario.id} - " +
                        "${funcionario.nome} - " +
                        "${funcionario.cargo} - " +
                        "${funcionario.telefone} - " +
                        "Setor: ${setor?.nome ?: "Não informado"}"
            )
        }
    }

    private fun cadastrar() {

        println()
        println("====== CADASTRAR FUNCIONÁRIO ======")

        print("Nome: ")
        val nome = readln()

        print("Cargo: ")
        val cargo = readln()

        print("Telefone: ")
        val telefone = readln()

        val setorId = escolherSetor() ?: return

        val funcionario = Funcionario(
            nome = nome,
            cargo = cargo,
            telefone = telefone,
            setorId = setorId
        )

        funcionarioService.cadastrar(funcionario)
    }

    private fun atualizar() {

        println()
        println("====== ATUALIZAR FUNCIONÁRIO ======")

        print("ID do funcionário: ")
        val id = readln().toIntOrNull()

        if (id == null) {
            println("ID inválido.")
            return
        }

        print("Novo nome: ")
        val nome = readln()

        print("Novo cargo: ")
        val cargo = readln()

        print("Novo telefone: ")
        val telefone = readln()

        val setorId = escolherSetor() ?: return

        val funcionario = Funcionario(
            id = id,
            nome = nome,
            cargo = cargo,
            telefone = telefone,
            setorId = setorId
        )

        funcionarioService.atualizar(funcionario)
    }

    private fun excluir() {

        println()
        println("====== EXCLUIR FUNCIONÁRIO ======")

        print("ID do funcionário: ")
        val id = readln().toIntOrNull()

        if (id == null) {
            println("ID inválido.")
            return
        }

        print("Tem certeza que deseja excluir este funcionário? (s/n): ")

        val confirmacao = readln()

        if (confirmacao.lowercase() == "s") {
            funcionarioService.excluir(id)
        } else {
            println("Exclusão cancelada.")
        }
    }

    private fun escolherSetor(): Int? {

        val setores = setorDAO.listar()

        if (setores.isEmpty()) {
            println("Nenhum setor cadastrado.")
            return null
        }

        println()
        println("====== ESCOLHER SETOR ======")

        for (setor in setores) {
            println("${setor.id} - ${setor.nome}")
        }

        print("ID do setor: ")

        val id = readln().toIntOrNull()

        if (id == null) {
            println("ID do setor inválido.")
            return null
        }

        if (setores.none { it.id == id }) {
            println("Setor não encontrado.")
            return null
        }

        return id
    }
}