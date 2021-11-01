package shared.forms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public abstract class FormPessoaBase extends FormBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtEmail;

	/**
	 * Create the frame.
	 */
	public FormPessoaBase() {
		setBounds(100, 100, 700, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setBounds(10, 85, 46, 14);
		getContentPane().add(lblName);
		
		txtName = new JTextField();
		lblName.setLabelFor(txtName);
		txtName.setBounds(10, 101, 326, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setLabelFor(txtEmail);
		lblEmail.setBounds(10, 132, 46, 14);
		getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 148, 326, 20);
		getContentPane().add(txtEmail);
		

	}
}
