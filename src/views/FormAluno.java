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
import shared.MessageConfirm;
import shared.AModel;
import shared.APessoa;
import shared.EMode;
import shared.NumberValidator;
import shared.Validator;
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
	
	private AlunoController alunoController = new AlunoController();

	private static final String TITLE_ERROR = "Erro com os dados do Aluno";
	private static final String TITLE_CONFIRM = null;
	private static final String TITLE_REMOVE = null;
	
	private static final String NameError = "Verifique o Nome desse Aluno";
	private static final String EmailError = "Verifique Email desse Aluno";
	private static final String RaError = "Verifique o RA desse Aluno";
	
	private int Id = 0;
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
		String[] alunoColumns = {"Id", "Nome", "Email", "RA"};
		this.setColumns(alunoColumns);
		
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
		txtRa.setBounds(10, 198, 369, 20);
		getContentPane().add(txtRa);
		txtRa.setColumns(10);

		LoadTable();
	}

	@Override
	public void BackHome() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Clear() {
		state = EMode.New;
		
		Id = 0;
		
		Name = "";
		txtName.setText(Name);

		Email = "";
		txtEmail.setText(Email);

		RA = "";
		txtRa.setText(RA);
	}

	@Override
	public void Remove() {
		int index = tblContent.getSelectedRow();
		
		if(index == -1)
			return;
		
		Aluno aluno = getAt(index);		
		String msgRemoverAluno = "Deseja Remover o aluno " + aluno.getNome() + "?";
		String msgAlunoRemoved = "Aluno " + aluno.getNome() + " removido com sucesso!";
		if(MessageConfirm.confirmDialog(this,  msgRemoverAluno, TITLE_REMOVE, msgAlunoRemoved, TITLE_CONFIRM)) {
			alunoController.Excluir(aluno);
			RefreshTable();
		}
		
	}

	protected Aluno getAt(int index) {
		int id = (int) tblContent.getValueAt(index, tblContent.getColumn("Id").getModelIndex());
		String name = (String) tblContent.getValueAt(index, tblContent.getColumn("Nome").getModelIndex());
		String email = (String) tblContent.getValueAt(index, tblContent.getColumn("Email").getModelIndex());
		String RA = (String) tblContent.getValueAt(index, tblContent.getColumn("RA").getModelIndex());
		return new Aluno(id, name, email, RA);
	}
	
	public Aluno fill() {
		Name = txtName.getText();
		Email = txtEmail.getText();
		RA = txtRa.getText();
		
		return new Aluno(Id, Name, Email, RA);
	}
	
	@Override
	protected void fill(APessoa model) {
		Aluno aluno = (Aluno) model;
		Id = aluno.getId();
		txtName.setText(aluno.getNome());
		txtEmail.setText(aluno.getEmail());
		txtRa.setText(aluno.getRegistro_academico());
	}
	

	@Override
	public boolean is_valid() {
		// TODO Auto-generated method stub
		boolean isValid = true;
		
		isValid &= NameIsValid();
		
		isValid &= EmailIsValid();
		
		isValid &= RaIsValid();
		
		return isValid;
	}
	private boolean NameIsValid() {
	
		if(!Validator.ValidName(txtName.getText())) {
			MessageError(NameError);
			txtName.requestFocus();
			return false;
		}
		return true;
	}


	private boolean EmailIsValid() {
		
		if(!Validator.ValidEmail(txtEmail.getText())) {
			MessageError(EmailError);
			txtEmail.requestFocus();
			return false;
		}
		return true;
	}

	private boolean RaIsValid() {
		
		if(!Validator.ValidNumber(txtRa.getText())) {
			MessageError(RaError);
			txtRa.requestFocus();
			return false;
		}
		return true;
	}
	
	public void MessageError(String message) {
		JOptionPane.showMessageDialog(this, message, TITLE_ERROR, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void FillTable(DefaultTableModel model) {
        var listaAlunos = alunoController.Lista();
        
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
