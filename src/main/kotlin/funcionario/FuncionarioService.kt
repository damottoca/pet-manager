package funcionario

class FuncionarioService(
    private val funcionarioDAO: FuncionarioDAO
) {

    fun cadastrar(funcionario: Funcionario) {

        if (funcionario.nome.isBlank()) {
            println("O nome do funcionário não pode ficar vazio.")
            return
        }

        if (funcionario.cargo.isBlank()) {
            println("O cargo do funcionário não pode ficar vazio.")
            return
        }

        funcionarioDAO.cadastrar(funcionario)

        println("Funcionário cadastrado com sucesso!")
    }

    fun listar(): List<Funcionario> {
        return funcionarioDAO.listar()
    }

    fun atualizar(funcionario: Funcionario) {

        if (funcionario.id == null) {
            println("ID do funcionário inválido.")
            return
        }

        if (funcionario.nome.isBlank()) {
            println("O nome do funcionário não pode ficar vazio.")
            return
        }

        if (funcionario.cargo.isBlank()) {
            println("O cargo do funcionário não pode ficar vazio.")
            return
        }

        funcionarioDAO.atualizar(funcionario)

        println("Funcionário atualizado com sucesso!")
    }

    fun excluir(id: Int) {

        if (id <= 0) {
            println("ID do funcionário inválido.")
            return
        }

        funcionarioDAO.excluir(id)

        println("Funcionário excluído com sucesso!")
    }
}