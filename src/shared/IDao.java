package shared;

import java.util.ArrayList;

public interface IDao<T> {
	public boolean Salvar(T model);
	public boolean Excluir(T model);
	public boolean Modificar(T model);
	public boolean Cria(T model);
	public ArrayList<T> Lista();
}
