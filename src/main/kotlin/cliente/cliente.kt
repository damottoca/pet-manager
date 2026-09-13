package cliente

data class Cliente(
    val id: Int? = null,
    val nome: String,
    val telefone: String?,
    val email: String?
)