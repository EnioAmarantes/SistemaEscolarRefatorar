package shared.consts;

import java.io.File;

public abstract class Config {
	
	public final static File PATHDB = new File(
			System.getProperty("user.dir")+
			"/src/shared/database/configBd.properties"
			);
}
