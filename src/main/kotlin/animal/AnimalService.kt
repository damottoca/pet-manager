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

    fun atualizar(animal: Animal) {

        if (animal.id == null) {
            println("ID do animal inválido.")
            return
        }

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

        animalDAO.atualizar(animal)

        println("Animal atualizado com sucesso!")
    }

    fun excluir(id: Int) {

        if (id <= 0) {
            println("ID do animal inválido.")
            return
        }

        animalDAO.excluir(id)

        println("Animal excluído com sucesso!")
    }
}