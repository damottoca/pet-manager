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
}