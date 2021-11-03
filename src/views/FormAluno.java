package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		this.TITLE = "Cadastro de Alunos";
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
