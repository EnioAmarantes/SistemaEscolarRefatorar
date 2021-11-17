package shared.database.migrations;

public class CreateProfessorTable extends AMigrate{

	public CreateProfessorTable() {
		this.table = "professor";
		this.sqlCreat = "CREATE table professor ( "
				+ "    id_professor INT NOT NULL AUTO_INCREMENT, "
				+ "    nome VARCHAR(100) NOT NULL, "
				+ "    email VARCHAR(50) NOT NULL, "
				+ "    disciplina VARCHAR(45) NOT NULL, "
				+ "    PRIMARY KEY(id_professor) "
				+ ");";
	}
}
