package animal

class AnimalMenu(
    private val animalService: AnimalService
) {

    fun exibir() {

        var continuar = true

        while (continuar) {

            println()
            println("====== ANIMAIS ======")
            println("1 - Listar animais")
            println("2 - Cadastrar animal")
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

        val animais = animalService.listar()

        println()
        println("====== ANIMAIS CADASTRADOS ======")

        if (animais.isEmpty()) {
            println("Nenhum animal cadastrado.")
            return
        }

        for (animal in animais) {
            println(
                "${animal.id} - ${animal.nome} - ${animal.especie} - " +
                        "${animal.raca} - ${animal.idade} anos - Cliente ID: ${animal.clienteId}"
            )
        }
    }

    private fun cadastrar() {

        println()
        println("====== CADASTRAR ANIMAL ======")

        print("Nome: ")
        val nome = readln()

        print("Espécie: ")
        val especie = readln()

        print("Raça: ")
        val raca = readln()

        print("Idade: ")
        val idade = readln().toIntOrNull()

        print("ID do cliente responsável: ")
        val clienteId = readln().toIntOrNull()

        if (clienteId == null) {
            println("ID do cliente inválido.")
            return
        }

        val animal = Animal(
            nome = nome,
            especie = especie,
            raca = raca,
            idade = idade,
            clienteId = clienteId
        )

        animalService.cadastrar(animal)
    }
}

