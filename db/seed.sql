INSERT INTO cliente (nome, telefone, email)
VALUES
    ('Carla', '(46) 99999-1111', 'carla@email.com'),
    ('João', '(46) 98888-2222', 'joao@email.com'),
    ('Marina', '(46) 97777-3333', 'marina@email.com');


INSERT INTO animal (nome, especie, raca, idade, cliente_id)
VALUES
    ('Thor', 'Cachorro', 'Golden Retriever', 4, 1),
    ('Mel', 'Cachorro', 'Shih-tzu', 2, 1),
    ('Nina', 'Gato', 'Siamês', 3, 2);


INSERT INTO servico (nome, descricao, preco)
VALUES
    ('Banho', 'Banho completo para o animal', 50.00),
    ('Tosa', 'Tosa conforme a preferência do cliente', 70.00),
    ('Banho e Tosa', 'Banho e tosa completos', 100.00),
    ('Hidratação', 'Hidratação dos pelos', 45.00);


INSERT INTO funcionario (nome, cargo, telefone)
VALUES
    ('Ana', 'Tosadora', '(46) 99999-4444'),
    ('Lucas', 'Atendente', '(46) 98888-5555'),
    ('Mariana', 'Gerente', '(46) 97777-6666');