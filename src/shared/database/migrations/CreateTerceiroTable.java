package shared.database.migrations;

public class CreateTerceiroTable extends AMigrate{

	public CreateTerceiroTable() {
		this.table = "terceiro";
		this.sqlCreat = "CREATE TABLE IF NOT EXISTS `terceiro` ( "
				+ "  `id_terceiro` INT NOT NULL AUTO_INCREMENT, "
				+ "  `nome` VARCHAR(100) NOT NULL, "
				+ "  `email` VARCHAR(50) NOT NULL, "
				+ "  `id_funcao` int NOT NULL, "
				+ "  PRIMARY KEY (`id_terceiro`), "
				+ "CONSTRAINT fk_terceiro_funcao "
				+ "	foreign key (`id_funcao`) "
				+ "	references `funcao` (`id_funcao`));";
	}
}
