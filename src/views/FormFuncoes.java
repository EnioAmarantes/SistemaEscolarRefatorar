package views;

import java.awt.EventQueue;

import javax.swing.table.DefaultTableModel;

import shared.AModel;
import shared.forms.FormBase;

public class FormFuncoes extends FormBase<AModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAluno frame = new FormAluno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void BackHome() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean is_valid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void FillTable(DefaultTableModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected AModel fill() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void fill(AModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected AModel getAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Clear() {
		// TODO Auto-generated method stub
		
	}

}
