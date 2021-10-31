package shared;

import lombok.Getter;
import lombok.Setter;

public abstract class AModel {
	public static final String STRING_EMPTY = "";
	
	@Getter
	public int id;
	@Getter @Setter
	public boolean removed;
}
