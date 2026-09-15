CREATE TABLE cliente (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         telefone VARCHAR(20),
                         email VARCHAR(100)
);

CREATE TABLE animal (
                        id SERIAL PRIMARY KEY,
                        nome VARCHAR(100) NOT NULL,
                        especie VARCHAR(50) NOT NULL,
                        raca VARCHAR(50),
                        idade INTEGER,
                        cliente_id INTEGER NOT NULL,

                        CONSTRAINT fk_animal_cliente
                            FOREIGN KEY (cliente_id)
                                REFERENCES cliente(id)
);

CREATE TABLE servico (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         descricao VARCHAR(255),
                         preco NUMERIC(10, 2) NOT NULL
);

CREATE TABLE funcionario (
                             id SERIAL PRIMARY KEY,
                             nome VARCHAR(100) NOT NULL,
                             cargo VARCHAR(50) NOT NULL,
                             telefone VARCHAR(20)
);

CREATE TABLE agendamento (
                             id SERIAL PRIMARY KEY,
                             data_hora TIMESTAMP NOT NULL,
                             animal_id INTEGER NOT NULL,
                             servico_id INTEGER NOT NULL,
                             funcionario_id INTEGER NOT NULL,
                             observacao VARCHAR(255),

                             CONSTRAINT fk_agendamento_animal
                                 FOREIGN KEY (animal_id)
                                     REFERENCES animal(id),

                             CONSTRAINT fk_agendamento_servico
                                 FOREIGN KEY (servico_id)
                                     REFERENCES servico(id),

                             CONSTRAINT fk_agendamento_funcionario
                                 FOREIGN KEY (funcionario_id)
                                     REFERENCES funcionario(id)
);