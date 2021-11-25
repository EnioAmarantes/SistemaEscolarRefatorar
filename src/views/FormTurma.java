package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.ProfessorController;
import controllers.TurmaController;
import models.Professor;
import models.Aluno;
import models.Turma;
import shared.EMode;
import shared.MessageConfirm;
import shared.forms.FormBase;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	
	protected JTextField txtTurma;
	protected JTextField txtCodigo;
	protected JTextField txtSala;
	protected JComboBox<Professor> jcbProfessor;

	private String Turma;
	private String Codigo;
	private String Sala;
	private Professor Professor;
	private int Id = 0;

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
		String[] alunoColumns = {"Id", "Turma", "Código", "Sala", "Professor/Disciplina", "Alunos Matriculados"};
		this.setColumns(alunoColumns);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		setThisTitle("Cadastro deTurmas");
		getContentPane().setLayout(null);
		
		JLabel lblTurma = new JLabel("Turma");
		lblTurma.setBounds(10, 79, 368, 14);
		getContentPane().add(lblTurma);
		
		txtTurma = new JTextField();
		txtTurma.setBounds(10, 96, 368, 20);
		getContentPane().add(txtTurma);
		txtTurma.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 127, 368, 14);
		getContentPane().add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(10, 144, 368, 20);
		getContentPane().add(txtCodigo);
		
		
		JLabel lblSala = new JLabel("Sala");
		lblSala.setBounds(10, 175, 368, 14);
		getContentPane().add(lblSala);
		
		txtSala = new JTextField();
		txtSala.setColumns(10);
		txtSala.setBounds(10, 192, 368, 20);
		getContentPane().add(txtSala);
		
		
		JLabel lblProfessor = new JLabel("Professor");
		lblProfessor.setBounds(10, 223, 368, 14);
		getContentPane().add(lblProfessor);
		
		jcbProfessor = new JComboBox<Professor>();
		jcbProfessor.setBounds(10, 240, 368, 20);
		getContentPane().add(jcbProfessor);
		
		JButton btnMatricularAlunos = new JButton("Matricular Alunos");
		btnMatricularAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tblContent.getSelectedRow();
				
				if(index == -1)
					return;
				
				String[] id_turma = {String.valueOf((int) tblContent.getValueAt(index, tblContent.getColumn("Id").getModelIndex()))};
				FormMatricula.main(id_turma);
			}
		});
		btnMatricularAlunos.setBounds(105, 406, 184, 51);
		getContentPane().add(btnMatricularAlunos);
		
		LoadTable();
		LoadProfessores();
	}
	private void LoadProfessores() {
		ProfessorController professorController = new ProfessorController();
		
		Vector<Professor> listaProfessor = new Vector<Professor>();
		
		for(Professor professor : professorController.Lista()) {
			listaProfessor.add(professor);
		}
		
		jcbProfessor.setModel(new DefaultComboBoxModel<Professor>(listaProfessor));
		
	}

	@Override
	public void BackHome() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Remove() {
		int index = tblContent.getSelectedRow();
		
		if(index == -1)
			return;
		
		Turma turma = getAt(index);		
		String msgRemoverAluno = "Deseja Remover o Turma " + turma.getNome() + "?";
		String msgAlunoRemoved = "Turma " + turma.getNome() + " removido com sucesso!";
		if(MessageConfirm.confirmDialog(this,  msgRemoverAluno, TITLE_REMOVE, msgAlunoRemoved, TITLE_CONFIRM)) {
			controller.Excluir(turma);
			RefreshTable();
		}
		
	}

	@Override
	public boolean is_valid() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void FillTable(DefaultTableModel model) {
		ArrayList<Turma> listaTurma = this.controller.Lista();
		
		for(Turma turma: listaTurma) {
			model.addRow(new Object[] {
					turma.getId(),
					turma.getNome(),
					turma.getCodigo(),
					turma.getSala(),
					turma.getProfessor().getNomeDisciplina(),
					turma.getAlunos().size()
			});
		}
		
	}

	@Override
	protected Turma fill() {
	
		Turma = txtTurma.getText();
		Codigo = txtCodigo.getText();
		Sala = txtSala.getText();
		Professor = (Professor) jcbProfessor.getSelectedItem();
		
		return new Turma(Id , Turma, Codigo, Sala, Professor, new ArrayList<Aluno>());
	}

	@Override
	protected void fill(Turma model) {
		Turma turma = (Turma) model;
		
		Turma = txtTurma.getText();
		Codigo = txtCodigo.getText();
		Sala = txtSala.getText();
		Professor = (Professor) jcbProfessor.getSelectedItem();
		
	}

	@Override
	protected Turma getAt(int index) {
		int id = (int) tblContent.getValueAt(index, tblContent.getColumn("Id").getModelIndex());
		String Turma = (String) tblContent.getValueAt(index, tblContent.getColumn("Turma").getModelIndex());
		String Codigo = (String) tblContent.getValueAt(index, tblContent.getColumn("Código").getModelIndex());
		String Sala = (String) tblContent.getValueAt(index, tblContent.getColumn("Sala").getModelIndex());
		Professor Professor = (Professor) tblContent.getValueAt(index, tblContent.getColumn("Professor/Disciplina").getModelIndex());
		
		return new Turma(id, Turma, Codigo, Sala, Professor, new ArrayList<Aluno>());
	}

	@Override
	public void Clear() {
		state = EMode.New;
		
		txtTurma.setText("");
		txtCodigo.setText("");
		txtSala.setText("");
		jcbProfessor.setSelectedIndex(0);
		
	}
	
	@Override
	public void LoadTable() {
		createTable(columns);
		this.tblContent.getColumnModel().getColumn(4).setPreferredWidth(115);
		this.tblContent.getColumnModel().getColumn(5).setPreferredWidth(113);
		this.scrollPane.setViewportView(this.tblContent);
		
		RefreshTable();
	}

}
