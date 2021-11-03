package shared.forms;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FormTeste extends FormPessoaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public FormTeste() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		
		this.TITLE = "Testando";
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
