import animal.AnimalDAO
import animal.AnimalMenu
import animal.AnimalService
import cliente.ClienteDAO
import cliente.ClienteMenu
import cliente.ClienteService
import funcionario.FuncionarioDAO
import funcionario.FuncionarioMenu
import funcionario.FuncionarioService
import servico.ServicoDAO
import servico.ServicoMenu
import servico.ServicoService

fun main() {

    println("================================")
    println("        🐾 PET MANAGER")
    println("================================")

    println()
    println("1 - Clientes")
    println("2 - Animais")
    println("3 - Serviços")
    println("4 - Funcionários")
    println("0 - Sair")
    println()

    print("Escolha uma opção: ")

    val opcao = readln()

    when (opcao) {

        "1" -> {
            val clienteDAO = ClienteDAO()
            val clienteService = ClienteService(clienteDAO)
            val clienteMenu = ClienteMenu(clienteService)

            clienteMenu.exibir()
        }

        "2" -> {
            val animalDAO = AnimalDAO()
            val animalService = AnimalService(animalDAO)
            val animalMenu = AnimalMenu(animalService)

            animalMenu.exibir()
        }

        "3" -> {
            val servicoDAO = ServicoDAO()
            val servicoService = ServicoService(servicoDAO)
            val servicoMenu = ServicoMenu(servicoService)

            servicoMenu.exibir()
        }

        "4" -> {
            val funcionarioDAO = FuncionarioDAO()
            val funcionarioService = FuncionarioService(funcionarioDAO)
            val funcionarioMenu = FuncionarioMenu(funcionarioService)

            funcionarioMenu.exibir()
        }

        "0" -> println("Saindo do PetManager...")

        else -> println("Opção inválida!")
    }
}