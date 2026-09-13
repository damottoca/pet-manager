package animal

class AnimalService(
    private val animalDAO: AnimalDAO
) {

    fun cadastrar(animal: Animal) {

        if (animal.nome.isBlank()) {
            println("O nome do animal não pode ficar vazio.")
            return
        }

        if (animal.especie.isBlank()) {
            println("A espécie do animal não pode ficar vazia.")
            return
        }

        if (animal.clienteId <= 0) {
            println("O animal precisa estar vinculado a um cliente válido.")
            return
        }

        animalDAO.cadastrar(animal)

        println("Animal cadastrado com sucesso!")
    }

    fun listar(): List<Animal> {
        return animalDAO.listar()
    }
}