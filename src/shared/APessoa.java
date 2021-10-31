package shared;

import lombok.Getter;
import lombok.Setter;

public abstract class APessoa extends AModel {
	@Getter @Setter
	private String nome = STRING_EMPTY;
	@Getter @Setter
	private String email = STRING_EMPTY;
}
