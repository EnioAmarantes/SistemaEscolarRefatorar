USE sistemaEscolar;

CREATE table aluno (
    id_aluno INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    registro_academico VARCHAR(45) NOT NULL,
    PRIMARY KEY(id_aluno)
);

INSERT INTO aluno (id_aluno, nome, email, registro_academico) VALUES (1, "Matheus Silva", "matheussilva@gmail.com", "1916041");
INSERT INTO aluno (id_aluno, nome, email, registro_academico) VALUES (2, "Fábio Silva", "fabiosilva@gmail.com", "1923233");