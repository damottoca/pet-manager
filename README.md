# 🐾 PetManager

Sistema de gerenciamento para pet shop desenvolvido em Kotlin, com integração ao PostgreSQL.

O projeto foi desenvolvido como forma de praticar programação orientada a objetos, acesso a banco de dados e organização de uma aplicação em camadas.

## 💻 Tecnologias

- Kotlin
- Gradle
- PostgreSQL
- JDBC
- IntelliJ IDEA
- Git e GitHub

## 📌 Funcionalidades

- Cadastro, consulta, atualização e exclusão de clientes
- Cadastro, consulta, atualização e exclusão de animais
- Cadastro, consulta, atualização e exclusão de serviços
- Cadastro, consulta, atualização e exclusão de funcionários
- Organização dos funcionários por setores
- Cadastro, consulta, atualização e exclusão de agendamentos
- Relacionamento entre animais e seus responsáveis
- Cadastro de vendas com um ou mais serviços
- Cálculo automático do total da venda
- Controle de entradas e saídas do caixa
- Registro de pagador, recebedor, data/hora, descrição e responsável pela movimentação
- Auditoria das movimentações financeiras
- Validação de dados utilizando REGEX, nullable e tratamento de exceções
- Transações no banco de dados para manter a consistência das operações

## 🗂️ Estrutura

O projeto utiliza uma organização baseada em responsabilidades:

```text
Menu → Service → DAO → JDBC → PostgreSQL
