package cliente

import pessoa.Pessoa

data class Cliente(
    override val id: Int? = null,
    override val nome: String,
    override val telefone: String?,
    val email: String?
) : Pessoa {

    override fun tipoPessoa(): String {
        return "Cliente"
    }
}