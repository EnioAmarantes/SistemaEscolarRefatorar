package shared.database.migrations;

public class CreateTurmaTable extends AMigrate{

	public CreateTurmaTable() {
		this.table = "turma";
		this.sqlCreat = "CREATE TABLE IF NOT EXISTS `turma` ( "
				+ "	`id_turma` INT NOT NULL AUTO_INCREMENT, "
				+ "	`codigo` VARCHAR(45) NOT NULL, "
				+ "	`nome` VARCHAR(45) NULL, "
				+ "	`sala` VARCHAR(45) NULL, "
				+ "	`ano` DATE NULL, "
				+ "	`id_professor` INT NOT NULL, "
				+ "PRIMARY KEY (`id_turma`), "
				+ "UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE, "
				+ "INDEX `fk_turma_professor_idx` (`id_professor` ASC) VISIBLE, "
				+ "CONSTRAINT `fk_turma_professor` "
				+ "	FOREIGN KEY (`id_professor`) "
				+ "	REFERENCES `professor` (`id_professor`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION);";
	}
}
