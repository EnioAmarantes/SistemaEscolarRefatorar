package shared.database.migrations;

public class CreateMatriculaTable extends AMigrate{

	public CreateMatriculaTable() {
		this.table = "matricula_turma_aluno";
		this.sqlCreat = "CREATE TABLE IF NOT EXISTS `matricula_turma_aluno` ("
				+ "	`id_matricula` int not null AUTO_INCREMENT, "
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
