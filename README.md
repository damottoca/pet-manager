🐾 PetManager
Sistema de gerenciamento para pet shop desenvolvido em Kotlin, com integração ao PostgreSQL.

O projeto foi desenvolvido como forma de praticar programação orientada a objetos, acesso a banco de dados e organização de uma aplicação em camadas.

💻 Tecnologias
Kotlin
Gradle
PostgreSQL
JDBC
IntelliJ IDEA
Git e GitHub
📌 Funcionalidades
Cadastro, consulta, atualização e exclusão de clientes
Cadastro, consulta, atualização e exclusão de animais
Cadastro, consulta, atualização e exclusão de serviços
Cadastro, consulta, atualização e exclusão de funcionários
Cadastro, consulta, atualização e exclusão de agendamentos
Relacionamento entre animais e seus responsáveis
Consulta de agendamentos com informações de animal, serviço e funcionário
🗂️ Estrutura
O projeto utiliza uma organização baseada em responsabilidades:

Menu → Service → DAO → JDBC → PostgreSQL
