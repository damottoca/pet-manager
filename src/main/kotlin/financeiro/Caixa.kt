package financeiro

import java.math.BigDecimal

class Caixa(
    private var saldo: BigDecimal
) {

    fun adicionar(valor: BigDecimal) {

        require(valor > BigDecimal.ZERO) {
            "O valor precisa ser maior que zero."
        }

        saldo = saldo.add(valor)
    }

    fun retirar(valor: BigDecimal) {

        require(valor > BigDecimal.ZERO) {
            "O valor precisa ser maior que zero."
        }

        require(valor <= saldo) {
            "Saldo insuficiente."
        }

        saldo = saldo.subtract(valor)
    }

    fun consultarSaldo(): BigDecimal {
        return saldo
    }
}