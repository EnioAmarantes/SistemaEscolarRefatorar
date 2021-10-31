package shared;

import java.util.ArrayList;

public interface IDatabase<IModel> extends IValidator {
	public boolean Salvar(IModel model);
	public boolean Excluir(IModel model);
	public boolean Modificar(IModel model);
	public boolean Cria(IModel model);
	public ArrayList<IModel> Lista();
}
