USE sistemaEscolar;

CREATE table terceiro (
    id_terceiro INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    funcao VARCHAR(45) NOT NULL,
    PRIMARY KEY(id_terceiro)
);

INSERT INTO terceiro (id_terceiro, nome, email, funcao) VALUES (1, "Maria Josefa", "josefa@gmail.com", "auxiliar de serviços gerais");
INSERT INTO terceiro (id_terceiro, nome, email, funcao) VALUES (2, "Sebastião Moraes", "bastiao@gmail.com", "auxiliar de serviços gerais");