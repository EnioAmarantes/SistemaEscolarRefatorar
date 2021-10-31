package shared;

import lombok.Getter;
import lombok.Setter;

public abstract class Pessoa extends Model {
	@Getter @Setter
	private String nome = STRING_EMPTY;
	@Getter @Setter
	private String email = STRING_EMPTY;
}
