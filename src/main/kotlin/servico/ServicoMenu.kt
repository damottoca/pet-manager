package servico

class ServicoMenu(
    private val servicoService: ServicoService
) {

    fun exibir() {

        var continuar = true

        while (continuar) {

            println()
            println("====== SERVIÇOS ======")
            println("1 - Listar serviços")
            println("2 - Cadastrar serviço")
            println("3 - Atualizar serviço")
            println("4 - Excluir serviço")
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

        val servicos = servicoService.listar()

        println()
        println("====== SERVIÇOS CADASTRADOS ======")

        if (servicos.isEmpty()) {
            println("Nenhum serviço cadastrado.")
            return
        }

        for (servico in servicos) {
            println(
                "${servico.id} - ${servico.nome} - " +
                        "${servico.descricao} - R$ ${servico.preco}"
            )
        }
    }

    private fun cadastrar() {

        println()
        println("====== CADASTRAR SERVIÇO ======")

        print("Nome: ")
        val nome = readln()

        print("Descrição: ")
        val descricao = readln()

        print("Preço: ")
        val precoTexto = readln().replace(",", ".")

        val preco = precoTexto.toBigDecimalOrNull()

        if (preco == null) {
            println("Preço inválido.")
            return
        }

        val servico = Servico(
            nome = nome,
            descricao = descricao,
            preco = preco
        )

        servicoService.cadastrar(servico)
    }

    private fun atualizar() {

        println()
        println("====== ATUALIZAR SERVIÇO ======")

        print("ID do serviço: ")
        val id = readln().toIntOrNull()

        if (id == null) {
            println("ID inválido.")
            return
        }

        print("Novo nome: ")
        val nome = readln()

        print("Nova descrição: ")
        val descricao = readln()

        print("Novo preço: ")
        val precoTexto = readln().replace(",", ".")

        val preco = precoTexto.toBigDecimalOrNull()

        if (preco == null) {
            println("Preço inválido.")
            return
        }

        val servico = Servico(
            id = id,
            nome = nome,
            descricao = descricao,
            preco = preco
        )

        servicoService.atualizar(servico)
    }

    private fun excluir() {

        println()
        println("====== EXCLUIR SERVIÇO ======")

        print("ID do serviço: ")
        val id = readln().toIntOrNull()

        if (id == null) {
            println("ID inválido.")
            return
        }

        print("Tem certeza que deseja excluir este serviço? (s/n): ")
        val confirmacao = readln()

        if (confirmacao.lowercase() == "s") {
            servicoService.excluir(id)
        } else {
            println("Exclusão cancelada.")
        }
    }
}