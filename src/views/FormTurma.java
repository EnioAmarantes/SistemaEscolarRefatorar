package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.TurmaController;
import models.Turma;
import shared.forms.FormBase;

public class FormTurma extends FormBase<Turma> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TurmaController turmaController = new TurmaController();
	
	private static final String TITLE_CONFIRM = null;
	private static final String TITLE_REMOVE = null;
	
	private static final String NameError = "Verifique o Nome dessa Turma";
	private static final String CodigoError = "Verifique código dessa Turma";
	private static final String SalaEror = "Verifique a sala dessa Turma";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTurma frame = new FormTurma();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FormTurma() {
		setSize(new Dimension(980, 500));
		this.scrollPane.setBounds(388, 47, 560,410);
		this.btnRefresh.setSize(560, 23);
		
		this.btnNew.setBounds(11, 371, 90, 29);
		this.btnEdit.setBounds(105, 371, 90, 29);
		this.btnClear.setBounds(197, 371, 90, 29);
		this.btnRemove.setBounds(290, 371, 90, 29);
		
		this.controller = turmaController;
		this.TITLE_ERROR = "Erro com os dados do Aluno";
		String[] alunoColumns = {"Id", "Turma", "Código", "Sala", "Ano", "Professor/Disciplina", "Alunos Matriculados"};
		this.setColumns(alunoColumns);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		setThisTitle("Cadastro deTurmas");
		getContentPane().setLayout(null);
		
		LoadTable();
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
	protected Turma fill() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void fill(Turma model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Turma getAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Clear() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void LoadTable() {
		createTable(columns);
		this.tblContent.getColumnModel().getColumn(5).setPreferredWidth(115);
		this.tblContent.getColumnModel().getColumn(6).setPreferredWidth(113);
		this.scrollPane.setViewportView(this.tblContent);
		
		RefreshTable();
	}

}
