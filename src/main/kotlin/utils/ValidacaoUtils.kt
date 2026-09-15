package utils

object ValidacaoUtils {

    private val emailRegex =
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    private val telefoneRegex =
        Regex("^\\(?\\d{2}\\)?\\s?9?\\d{4,5}-?\\d{4}$")

    fun emailValido(email: String): Boolean {

        if (email.isBlank()) {
            return true
        }

        return emailRegex.matches(email)
    }

    fun telefoneValido(telefone: String): Boolean {

        if (telefone.isBlank()) {
            return true
        }

        return telefoneRegex.matches(telefone)
    }
}