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
}