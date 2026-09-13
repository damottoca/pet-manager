package cliente

class ClienteService(
    private val clienteDAO: ClienteDAO
) {

    fun cadastrar(cliente: Cliente) {

        if (cliente.nome.isBlank()) {
            println("O nome do cliente não pode ficar vazio.")
            return
        }

        clienteDAO.cadastrar(cliente)

        println("Cliente cadastrado com sucesso!")
    }

    fun listar(): List<Cliente> {
        return clienteDAO.listar()
    }

    fun atualizar(cliente: Cliente) {

        if (cliente.id == null) {
            println("ID do cliente inválido.")
            return
        }

        if (cliente.nome.isBlank()) {
            println("O nome do cliente não pode ficar vazio.")
            return
        }

        clienteDAO.atualizar(cliente)

        println("Cliente atualizado com sucesso!")
    }

    fun excluir(id: Int) {

        if (id <= 0) {
            println("ID do cliente inválido.")
            return
        }

        clienteDAO.excluir(id)

        println("Cliente excluído com sucesso!")
    }
}