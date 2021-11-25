package shared.database.migrations;

public class CreateFuncaoTable extends AMigrate{

	public CreateFuncaoTable() {
		this.table = "funcao";
		this.sqlCreat = "CREATE TABLE IF NOT EXISTS  `funcao` ( "
				+ "  `id_funcao` INT NOT NULL AUTO_INCREMENT, "
				+ "  `nome` VARCHAR(100) NOT NULL, "
				+ "  PRIMARY KEY (`id_funcao`));";
	}
}
