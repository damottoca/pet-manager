package cliente

import pessoa.Pessoa

class ClienteMenu(
    private val clienteService: ClienteService
) {

    fun exibir() {

        var continuar = true

        while (continuar) {

            println()
            println("====== CLIENTES ======")
            println("1 - Listar clientes")
            println("2 - Cadastrar cliente")
            println("3 - Atualizar cliente")
            println("4 - Excluir cliente")
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

        val clientes = clienteService.listar()

        println()
        println("====== CLIENTES CADASTRADOS ======")

        if (clientes.isEmpty()) {
            println("Nenhum cliente cadastrado.")
            return
        }

        for (cliente in clientes) {

            val pessoa: Pessoa = cliente

            println(
                "${pessoa.tipoPessoa()} - " +
                        "${cliente.id} - " +
                        "${cliente.nome} - " +
                        "${cliente.telefone} - " +
                        "${cliente.email}"
            )
        }
    }

    private fun cadastrar() {

        println()
        println("====== CADASTRAR CLIENTE ======")

        print("Nome: ")
        val nome = readln()

        print("Telefone: ")
        val telefone = readln()

        print("E-mail: ")
        val email = readln()

        val cliente = Cliente(
            nome = nome,
            telefone = telefone,
            email = email
        )

        clienteService.cadastrar(cliente)
    }

    private fun atualizar() {

        println()
        println("====== ATUALIZAR CLIENTE ======")

        print("ID do cliente: ")
        val id = readln().toIntOrNull()

        if (id == null) {
            println("ID inválido.")
            return
        }

        print("Novo nome: ")
        val nome = readln()

        print("Novo telefone: ")
        val telefone = readln()

        print("Novo e-mail: ")
        val email = readln()

        val cliente = Cliente(
            id = id,
            nome = nome,
            telefone = telefone,
            email = email
        )

        clienteService.atualizar(cliente)
    }

    private fun excluir() {

        println()
        println("====== EXCLUIR CLIENTE ======")

        print("ID do cliente: ")
        val id = readln().toIntOrNull()

        if (id == null) {
            println("ID inválido.")
            return
        }

        print("Tem certeza que deseja excluir este cliente? (s/n): ")
        val confirmacao = readln()

        if (confirmacao.lowercase() == "s") {
            clienteService.excluir(id)
        } else {
            println("Exclusão cancelada.")
        }
    }
}