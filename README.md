# 🐾 PetManager

Sistema de gerenciamento para pet shop desenvolvido em Kotlin, com integração ao PostgreSQL.

O projeto foi desenvolvido como atividade prática para trabalhar programação, banco de dados, JDBC, organização em camadas e regras de negócio.

## 💻 Tecnologias

- Kotlin
- Gradle
- PostgreSQL
- JDBC
- IntelliJ IDEA
- Git
- GitHub

## 📌 Funcionalidades

### Clientes
- Cadastro de clientes
- Listagem
- Atualização
- Exclusão
- Validação de e-mail e telefone

### Animais
- Cadastro de animais
- Vinculação do animal ao cliente responsável
- Listagem
- Atualização
- Exclusão

### Serviços
- Cadastro de serviços
- Descrição e preço
- Listagem
- Atualização
- Exclusão

### Funcionários
- Cadastro de funcionários
- Definição de cargo
- Organização por setores
- Listagem
- Atualização
- Exclusão

### Agendamentos
- Cadastro de agendamentos
- Data e hora
- Animal
- Serviço
- Funcionário responsável
- Observação
- Atualização
- Exclusão

### Vendas
- Seleção do cliente
- Seleção do animal
- Seleção de um ou mais serviços
- Definição da quantidade
- Funcionário responsável
- Confirmação antes da venda
- Cálculo automático do total
- Registro da venda no banco

### Financeiro
- Registro de entradas
- Registro de saídas
- Controle de saldo do caixa
- Pagador
- Recebedor
- Data e hora
- Descrição
- Funcionário responsável
- Validação para impedir saldo negativo
- Auditoria das movimentações

## 🗂️ Organização do projeto

O projeto utiliza uma organização baseada em responsabilidades:

```text
Menu
  ↓
Service
  ↓
DAO
  ↓
JDBC
  ↓
PostgreSQL
