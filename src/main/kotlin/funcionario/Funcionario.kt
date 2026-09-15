package funcionario

import pessoa.Pessoa

data class Funcionario(
    override val id: Int? = null,
    override val nome: String,
    override val telefone: String?,
    val cargo: String,
    val setorId: Int
) : Pessoa {

    override fun tipoPessoa(): String {
        return "Funcionário"
    }
}