package shared.forms;

import javax.swing.JLabel;
import javax.swing.JTextField;

import shared.ITable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public abstract class FormPessoaBase extends FormBase implements ITable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String Name = "";
	protected String Email = "";
	protected JTextField txtName;
	protected JTextField txtEmail;

	/**
	 * Create the frame.
	 */
	public FormPessoaBase() {
		setBounds(100, 100, 700, 400);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setBounds(10, 85, 46, 14);
		getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				txtName.setText(Name);
			}
		});
		lblName.setLabelFor(txtName);
		txtName.setBounds(10, 101, 326, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setLabelFor(txtEmail);
		lblEmail.setBounds(10, 132, 46, 14);
		getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				txtEmail.setText(Email);
			}
		});
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 148, 326, 20);
		getContentPane().add(txtEmail);
		

	}
}
