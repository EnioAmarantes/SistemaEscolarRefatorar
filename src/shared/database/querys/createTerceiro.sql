USE sistemaEscolar;

CREATE table professor (
    id_professor INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    disciplina VARCHAR(45) NOT NULL,
    PRIMARY KEY(id_professor)
);

INSERT INTO professor (id_professor, nome, email, disciplina) VALUES (1, "José Antônio", "zegatinho@gmail.com", "programação orientada a objetos");
INSERT INTO professor (id_professor, nome, email, disciplina) VALUES (2, "Fabrício Lopes", "fabrilopes@gmail.com", "programação desktop");