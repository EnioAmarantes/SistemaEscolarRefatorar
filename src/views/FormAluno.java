package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.AlunoController;
import models.Aluno;
import shared.NumberValidator;
import shared.forms.FormPessoaBase;

import javax.swing.JOptionPane;

public class FormAluno extends FormPessoaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	
	AlunoController alunoController = new AlunoController();

	private String RA = "";
	private JTextField txtRa;

	private String[] columns = { "Id", "Nome", "Email", "RA" };

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
		txtRa.setDocument(new NumberValidator());
		lblRa.setLabelFor(txtRa);
		txtRa.setBounds(10, 198, 326, 20);
		getContentPane().add(txtRa);
		txtRa.setColumns(10);
		Name = "Nome do Aluno";

		LoadTable();
	}

	@Override
	public void BackHome() {
		// TODO Auto-generated method stub

	}

	@Override
	public void New() {
		// TODO Auto-generated method stub
		Aluno aluno = fill();
		alunoController.Cria(aluno);
		RefreshTable();
		Clear();
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
		if (this.FieldIsEmpty()) {
			JOptionPane.showMessageDialog(null, "Um ou mais campos estão vazios, \n todos os campos são obrigatórios");
		} else {
			// TODO Auto-generated method stub
		}

	}

	@Override
	public void Update() {
		if (this.FieldIsEmpty()) {
			JOptionPane.showMessageDialog(null, "Um ou mais campos estão vazios, \n todos os campos são obrigatórios");
		} else {
			// TODO Auto-generated method stub
		}
	}

	@Override
	public void Remove() {
		// TODO Auto-generated method stub

	}

	public boolean FieldIsEmpty() {
		return (txtName.getText().equals("") || txtEmail.getText().equals("") || txtRa.getText().equals(""));
	}

	@Override
	public void LoadTable() {
		createTable(columns);
		this.scrollPane.setViewportView(this.tblContent);
		
		RefreshTable();
	}
	
	public Aluno fill() {
		Name = txtName.getText();
		Email = txtEmail.getText();
		RA = txtRa.getText();
		
		return new Aluno(1, Name, Email, RA);
	}
	
	public void RefreshTable() {
		DefaultTableModel model = (DefaultTableModel) this.tblContent.getModel();

        var listaAlunos = alunoController.Lista();

        model.setRowCount(0);
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        for (Aluno aluno : listaAlunos) {
        	
            model.addRow(new Object[]{
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getRegistro_academico()
            });
        }
        
        listaAlunos.clear();
	}
}
