package shared;

import java.util.ArrayList;

public interface IDao<T> {
	public T Excluir(T model);
	public T Modificar(T model);
	public boolean Cria(T model);
	public ArrayList<T> Lista();
	public boolean IsValid(T model);
}
