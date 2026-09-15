package pessoa

interface Pessoa {

    val id: Int?
    val nome: String
    val telefone: String?

    fun tipoPessoa(): String
}