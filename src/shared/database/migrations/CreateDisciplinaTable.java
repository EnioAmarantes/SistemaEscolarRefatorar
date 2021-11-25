package shared.database.migrations;

public class CreateDisciplinaTable extends AMigrate{

	public CreateDisciplinaTable() {
		this.table = "disciplina";
		this.sqlCreat = "CREATE TABLE IF NOT EXISTS  `disciplina` ( "
				+ "  `id_disciplina` INT NOT NULL AUTO_INCREMENT, "
				+ "  `nome` VARCHAR(100) NOT NULL, "
				+ "  PRIMARY KEY (`id_disciplina`));";
	}
}
