import agendamento.AgendamentoDAO
import agendamento.AgendamentoMenu
import agendamento.AgendamentoService

import animal.AnimalDAO
import animal.AnimalMenu
import animal.AnimalService

import cliente.ClienteDAO
import cliente.ClienteMenu
import cliente.ClienteService

import financeiro.AuditoriaService
import financeiro.MovimentacaoDAO
import financeiro.MovimentacaoMenu
import financeiro.MovimentacaoService

import funcionario.FuncionarioDAO
import funcionario.FuncionarioMenu
import funcionario.FuncionarioService

import servico.ServicoDAO
import servico.ServicoMenu
import servico.ServicoService

import venda.VendaDAO
import venda.VendaMenu
import venda.VendaService

fun main() {

    var continuar = true

    while (continuar) {

        println()
        println("================================")
        println("        🐾 PET MANAGER")
        println("================================")

        println()
        println("1 - Clientes")
        println("2 - Animais")
        println("3 - Serviços")
        println("4 - Funcionários")
        println("5 - Agendamentos")
        println("6 - Financeiro")
        println("7 - Vendas")
        println("0 - Sair")
        println()

        print("Escolha uma opção: ")

        when (readln()) {

            "1" -> {

                val clienteDAO = ClienteDAO()

                val clienteService =
                    ClienteService(clienteDAO)

                val clienteMenu =
                    ClienteMenu(clienteService)

                clienteMenu.exibir()
            }

            "2" -> {

                val animalDAO = AnimalDAO()

                val animalService =
                    AnimalService(animalDAO)

                val animalMenu =
                    AnimalMenu(animalService)

                animalMenu.exibir()
            }

            "3" -> {

                val servicoDAO = ServicoDAO()

                val servicoService =
                    ServicoService(servicoDAO)

                val servicoMenu =
                    ServicoMenu(servicoService)

                servicoMenu.exibir()
            }

            "4" -> {

                val funcionarioDAO =
                    FuncionarioDAO()

                val funcionarioService =
                    FuncionarioService(
                        funcionarioDAO
                    )

                val funcionarioMenu =
                    FuncionarioMenu(
                        funcionarioService
                    )

                funcionarioMenu.exibir()
            }

            "5" -> {

                val agendamentoDAO =
                    AgendamentoDAO()

                val agendamentoService =
                    AgendamentoService(
                        agendamentoDAO
                    )

                val animalDAO = AnimalDAO()

                val animalService =
                    AnimalService(
                        animalDAO
                    )

                val servicoDAO =
                    ServicoDAO()

                val servicoService =
                    ServicoService(
                        servicoDAO
                    )

                val funcionarioDAO =
                    FuncionarioDAO()

                val funcionarioService =
                    FuncionarioService(
                        funcionarioDAO
                    )

                val agendamentoMenu =
                    AgendamentoMenu(
                        agendamentoService,
                        animalService,
                        servicoService,
                        funcionarioService
                    )

                agendamentoMenu.exibir()
            }

            "6" -> {

                val movimentacaoDAO =
                    MovimentacaoDAO()

                val movimentacaoService =
                    MovimentacaoService(
                        movimentacaoDAO
                    )

                val funcionarioDAO =
                    FuncionarioDAO()

                val funcionarioService =
                    FuncionarioService(
                        funcionarioDAO
                    )

                val auditoriaService =
                    AuditoriaService(
                        movimentacaoService
                    )

                val movimentacaoMenu =
                    MovimentacaoMenu(
                        movimentacaoService,
                        funcionarioService,
                        auditoriaService
                    )

                movimentacaoMenu.exibir()
            }

            "7" -> {

                val vendaDAO =
                    VendaDAO()

                val servicoDAO =
                    ServicoDAO()

                val servicoService =
                    ServicoService(
                        servicoDAO
                    )

                val vendaService =
                    VendaService(
                        vendaDAO,
                        servicoService
                    )

                val clienteDAO =
                    ClienteDAO()

                val clienteService =
                    ClienteService(
                        clienteDAO
                    )

                val animalDAO =
                    AnimalDAO()

                val animalService =
                    AnimalService(
                        animalDAO
                    )

                val funcionarioDAO =
                    FuncionarioDAO()

                val funcionarioService =
                    FuncionarioService(
                        funcionarioDAO
                    )

                val vendaMenu =
                    VendaMenu(
                        vendaService,
                        clienteService,
                        animalService,
                        servicoService,
                        funcionarioService
                    )

                vendaMenu.exibir()
            }

            "0" -> {

                println()
                println(
                    "🐾 Obrigada por usar o PetManager!"
                )
                println("Até mais!")

                continuar = false
            }

            else -> {

                println()
                println("Opção inválida!")
            }
        }
    }
}