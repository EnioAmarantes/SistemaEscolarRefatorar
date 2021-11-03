package views;

import java.awt.EventQueue;

import shared.forms.FormPessoaBase;

public class FormAluno extends FormPessoaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	

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

	/**
	 * Create the frame.
	 */
	public FormAluno() {
		TITLE = "Testando";
	}

	public void BackHome() {
		// TODO Auto-generated method stub
		
	}

	public void New() {
		// TODO Auto-generated method stub
		
	}

	public void Edit() {
		// TODO Auto-generated method stub
		
	}

	public void Cancel() {
		// TODO Auto-generated method stub
		
	}

	public void Clear() {
		// TODO Auto-generated method stub
		
	}

	public void Create() {
		// TODO Auto-generated method stub
		
	}

	public void Update() {
		// TODO Auto-generated method stub
		
	}

	public void Remove() {
		// TODO Auto-generated method stub
		
	}

}
