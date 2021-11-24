package shared.database.migrations;

public class CreateTurmaTable extends AMigrate{

	public CreateTurmaTable() {
		this.table = "turma";
		this.sqlCreat = "CREATE TABLE IF NOT EXISTS `turma` ( "
				+ "	`id_turma` INT NOT NULL, "
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
				+ "ON UPDATE NO ACTION);"
				+ ""
				+ "CREATE TABLE IF NOT EXISTS `matricula_turma_aluno` ("
				+ "	`id_matricula` int not null, "
				+ "	`id_turma` int not null, "
				+ "	`id_aluno` int not null, "
				+ "PRIMARY KEY (`id_matricula`), "
				+ "CONSTRAINT `fk_matricula_turma` "
				+ "	FOREIGN KEY (`id_turma`) "
				+ "	REFERENCES `turma` (`id_turma`), "
				+ "CONSTRAINT `fk_matricula_aluno` "
				+ "	FOREIGN KEY (`id_aluno`) "
				+ "	REFERENCES `aluno` (`id_aluno`));";
	}
}
