package shared.database.migrations;

public class CreateTerceiroTable extends AMigrate{

	public CreateTerceiroTable() {
		this.table = "terceiro";
		this.sqlCreat = "CREATE table terceiro ( "
				+ "    id_terceiro INT NOT NULL AUTO_INCREMENT, "
				+ "    nome VARCHAR(100) NOT NULL, "
				+ "    email VARCHAR(50) NOT NULL, "
				+ "    funcao VARCHAR(45) NOT NULL, "
				+ "    PRIMARY KEY(id_terceiro) ;"
				+ ");";
	}
}
