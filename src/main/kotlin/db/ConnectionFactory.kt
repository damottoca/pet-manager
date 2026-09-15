package db

import java.sql.Connection
import java.sql.DriverManager

object ConnectionFactory {

    private const val URL =
        "jdbc:postgresql://localhost:5432/pet_manager"

    private const val USER =
        "postgres"

    private val PASSWORD =
        System.getenv("PET_MANAGER_DB_PASSWORD")

    fun conectar(): Connection {

        if (PASSWORD.isNullOrBlank()) {
            throw IllegalStateException(
                "A variável PET_MANAGER_DB_PASSWORD não foi configurada."
            )
        }

        return DriverManager.getConnection(
            URL,
            USER,
            PASSWORD
        )
    }
}