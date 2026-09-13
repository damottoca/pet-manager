package funcionario

class FuncionarioMenu(
    private val funcionarioService: FuncionarioService
) {

    fun exibir() {

        var continuar = true

        while (continuar) {

            println()
            println("====== FUNCIONÁRIOS ======")
            println("1 - Cadastrar funcionário")
            println("0 - Voltar")
            println()

            print("Escolha uma opção: ")

            when (readln()) {

                "1" -> cadastrar()

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
        println("====== CADASTRAR FUNCIONÁRIO ======")

        print("Nome: ")
        val nome = readln()

        print("Cargo: ")
        val cargo = readln()

        print("Telefone: ")
        val telefone = readln()

        val funcionario = Funcionario(
            nome = nome,
            cargo = cargo,
            telefone = telefone
        )

        funcionarioService.cadastrar(funcionario)
    }
}