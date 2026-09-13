package db

import java.sql.Connection
import java.sql.DriverManager

object ConnectionFactory {

    private const val URL = "jdbc:postgresql://localhost:5432/pet_manager"
    private const val USER = "postgres"
    private const val PASSWORD = "postgre"

    fun conectar(): Connection {
        return DriverManager.getConnection(URL, USER, PASSWORD)
    }
}