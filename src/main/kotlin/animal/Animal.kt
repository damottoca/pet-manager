package animal

data class Animal(
    val id: Int? = null,
    val nome: String,
    val especie: String,
    val raca: String?,
    val idade: Int?,
    val clienteId: Int
)