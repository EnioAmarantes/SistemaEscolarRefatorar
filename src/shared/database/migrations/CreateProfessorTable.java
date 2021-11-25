package shared.database.migrations;

public class CreateProfessorTable extends AMigrate{

	public CreateProfessorTable() {
		this.table = "professor";
		this.sqlCreat = "CREATE TABLE IF NOT EXISTS `professor` ( "
				+ "  `id_professor` INT NOT NULL AUTO_INCREMENT, "
				+ "  `nome` VARCHAR(100) NOT NULL, "
				+ "  `email` VARCHAR(50) NOT NULL, "
				+ "  `id_disciplina` int NOT NULL, "
				+ "  PRIMARY KEY (`id_professor`), "
				+ "	CONSTRAINT `fk_professor_disciplina` "
				+ "	FOREIGN KEY (`id_disciplina`) "
				+ "	REFERENCES `disciplina` (`id_disciplina`));";
	}
}
