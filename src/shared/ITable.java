package shared;

import javax.swing.table.DefaultTableModel;

public interface ITable {
	public void LoadTable();
	public void RefreshTable();
	public void FillTable(DefaultTableModel model);
}
