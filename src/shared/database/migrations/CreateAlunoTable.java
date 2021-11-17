package shared.database.migrations;

public class CreateAlunoTable extends AMigrate{

	public CreateAlunoTable() {
		this.table = "aluno";
		this.sqlCreat = "CREATE table aluno ("
				+ "    id_aluno INT NOT NULL AUTO_INCREMENT, "
				+ "    nome VARCHAR(100) NOT NULL, "
				+ "    email VARCHAR(50) NOT NULL, "
				+ "    registro_academico VARCHAR(45) NOT NULL, "
				+ "    PRIMARY KEY(id_aluno) "
				+ ");";
	}
}
