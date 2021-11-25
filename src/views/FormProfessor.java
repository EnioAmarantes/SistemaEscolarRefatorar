package views;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.ProfessorController;
import models.Aluno;
import models.Professor;
import shared.APessoa;
import shared.EMode;
import shared.MessageConfirm;
import shared.Validator;
import shared.forms.FormPessoaBase;

public class FormProfessor extends FormPessoaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProfessorController professorController = new ProfessorController();

	private static final String TITLE_CONFIRM = null;
	private static final String TITLE_REMOVE = null;
	
	private static final String NameError = "Verifique o Nome desse Professor";
	private static final String EmailError = "Verifique Email desse Professor";
	private static final String DisciplinaError = "Verifique a Disciplina desse Professor";
	
	private int Id = 0;
	private String Disciplina = "";
	private JTextField txtDisciplina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormProfessor frame = new FormProfessor();
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
	public FormProfessor() {
		
		setSize(new Dimension(980, 400));
		this.scrollPane.setBounds(388, 47, 560, 300);
		this.btnRefresh.setSize(560, 23);
		
		this.controller = professorController;
		this.TITLE_ERROR = "Erro com os dados do Professor";
		String[] professorColumns = {"Id", "Nome", "Email", "Disciplina"};
		this.setColumns(professorColumns);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		setThisTitle("Cadastro de Professores");
		getContentPane().setLayout(null);

		JLabel lblRa = new JLabel("Disciplina");
		lblRa.setBounds(10, 178, 94, 14);
		getContentPane().add(lblRa);

		txtDisciplina = new JTextField();
		lblRa.setLabelFor(txtDisciplina);
		txtDisciplina.setBounds(10, 198, 369, 20);
		getContentPane().add(txtDisciplina);
		txtDisciplina.setColumns(10);

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

		Disciplina = "";
		txtDisciplina.setText(Disciplina);
	}

	@Override
	public void Remove() {
		int index = tblContent.getSelectedRow();
		
		if(index == -1)
			return;
		
		Professor professor = getAt(index);		
		String msgRemoverProfessor = "Deseja Remover o professor " + professor.getNome() + "?";
		String msgProfessorRemoved = "Professor " + professor.getNome() + " removido com sucesso!";
		if(MessageConfirm.confirmDialog(this,  msgRemoverProfessor, TITLE_REMOVE, msgProfessorRemoved, TITLE_CONFIRM)) {
			professorController.Excluir(professor);
			RefreshTable();
		}
	}

	@Override
	public boolean is_valid() {
		boolean isValid = true;
		
		isValid &= NameIsValid();
		
		isValid &= EmailIsValid();
		
		isValid &= DisciplinaIsValid();
		
		return isValid;
	}

	@Override
	public void FillTable(DefaultTableModel model) {
		
		this.tblContent.getColumnModel().getColumn(0).setPreferredWidth(10);
		this.tblContent.getColumnModel().getColumn(3).setPreferredWidth(113);
		
        var listaProfessores = professorController.Lista();
        
        for (Professor professor : listaProfessores) {
        	
            model.addRow(new Object[]{
        		professor.getId(),
        		professor.getNome(),
        		professor.getEmail(),
        		professor.getDisciplina()
            });
        }
        
        listaProfessores.clear();
	}
	
	protected Professor getAt(int index) {
		int id = (int) tblContent.getValueAt(index, tblContent.getColumn("Id").getModelIndex());
		String name = (String) tblContent.getValueAt(index, tblContent.getColumn("Nome").getModelIndex());
		String email = (String) tblContent.getValueAt(index, tblContent.getColumn("Email").getModelIndex());
		String disciplina = (String) tblContent.getValueAt(index, tblContent.getColumn("Disciplina").getModelIndex());
		return new Professor(id, name, email, disciplina);
	}
	
	public Professor fill() {
		Name = txtName.getText();
		Email = txtEmail.getText();
		Disciplina = txtDisciplina.getText();
		
		return new Professor(Id, Name, Email, Disciplina);
	}
	
	@Override
	protected void fill(APessoa model) {
		Professor professor = (Professor) model;
		Id = professor.getId();
		txtName.setText(professor.getNome());
		txtEmail.setText(professor.getEmail());
		txtDisciplina.setText(professor.getDisciplina());
	}
	
	private boolean NameIsValid() {
		
		if(!Validator.ValidName(txtName.getText())) {
			MessageConfirm.messageError(NameError, TITLE_ERROR);
			txtName.requestFocus();
			return false;
		}
		return true;
	}


	private boolean EmailIsValid() {
		ArrayList<Professor> professores = controller.Lista();
		String email = txtEmail.getText();
		
		if(!Validator.ValidEmail(txtEmail.getText())) {
			MessageConfirm.messageError(EmailError, TITLE_ERROR);
			txtEmail.requestFocus();
			return false;
		}
		
		for(Professor professor : professores) {
			if(professor.getEmail().equals(email)) {
				ERROR_MESSAGE = "O email " + email + " j� est� cadastrado";
				return false;
			}
		}
		return true;
	}

	private boolean DisciplinaIsValid() {
		
		if(!Validator.ValidName(txtDisciplina.getText())) {
			MessageConfirm.messageError(DisciplinaError, TITLE_ERROR);
			txtDisciplina.requestFocus();
			return false;
		}
		return true;
	}

}
