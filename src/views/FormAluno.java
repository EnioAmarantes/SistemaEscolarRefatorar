package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shared.forms.FormPessoaBase;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormAluno extends FormPessoaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	
	private String RA = "";
	private JTextField txtRa;
	

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
		
		setThisTitle("Cadastro de Alunos");
		getContentPane().setLayout(null);
		
		JLabel lblRa = new JLabel("RA");
		lblRa.setBounds(10, 178, 46, 14);
		getContentPane().add(lblRa);
		
		txtRa = new JTextField();
		lblRa.setLabelFor(txtRa);
		txtRa.setBounds(10, 198, 326, 20);
		getContentPane().add(txtRa);
		txtRa.setColumns(10);
		Name = "Nome do Aluno";
	}

	@Override
	public void BackHome() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void New() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void Edit() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void Cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Clear() {
		Name = "";
		txtName.setText(Name);
		
		Email = "";
		txtEmail.setText(Email);
		
		RA = "";
		txtRa.setText(RA);
	}
	
	@Override
	public void Create() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void Remove() {
		// TODO Auto-generated method stub
		
	}
}
