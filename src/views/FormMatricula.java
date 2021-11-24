package views;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.EventQueue;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import controllers.MatriculaController;
import models.Aluno;
import shared.MessageConfirm;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormMatricula extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblAlunos;
	private JTable tblAlunosMatricular;
	
	private MatriculaController matriculaController;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMatricula frame = new FormMatricula(Integer.parseInt(args[0]));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FormMatricula(int id_turma) {
		
		this.matriculaController = new MatriculaController(id_turma);
		
		setMinimumSize(new Dimension(600, 420));
		setTitle("Matricular Alunos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormMatricula.class.getResource("/shared/icons/estudando.png")));
		getContentPane().setLayout(null);
		
		tblAlunos = new JTable();
		tblAlunos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Aluno", "Email", "RA"
			}
		));
		tblAlunos.getColumnModel().getColumn(1).setPreferredWidth(119);
		tblAlunos.getColumnModel().getColumn(2).setPreferredWidth(170);
		tblAlunos.getColumnModel().getColumn(3).setPreferredWidth(108);
		tblAlunos.setBounds(10, 39, 250, 271);
		getContentPane().add(tblAlunos);
		
		tblAlunosMatricular = new JTable();
		tblAlunosMatricular.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id", "Aluno", "Email", "RA"
				}
			));
		tblAlunosMatricular.getColumnModel().getColumn(1).setPreferredWidth(119);
		tblAlunosMatricular.getColumnModel().getColumn(2).setPreferredWidth(170);
		tblAlunosMatricular.getColumnModel().getColumn(3).setPreferredWidth(108);
		tblAlunosMatricular.setBounds(301, 39, 250, 271);
		getContentPane().add(tblAlunosMatricular);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(431, 342, 120, 30);
		getContentPane().add(btnCancelar);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Save();
			}
		});
		btnGravar.setBounds(301, 342, 120, 30);
		getContentPane().add(btnGravar);
		
		JLabel lblAlunos = new JLabel("Alunos");
		lblAlunos.setLabelFor(tblAlunos);
		lblAlunos.setBounds(10, 21, 250, 14);
		getContentPane().add(lblAlunos);
		
		JLabel lblAlunosMatricular = new JLabel("Alunos Matricular");
		lblAlunosMatricular.setLabelFor(tblAlunosMatricular);
		lblAlunosMatricular.setBounds(301, 21, 250, 14);
		getContentPane().add(lblAlunosMatricular);
		
		JLabel lblMatricularTodos = new JLabel(">>");
		lblMatricularTodos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MatricularTodos();
			}
		});
		lblMatricularTodos.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblMatricularTodos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatricularTodos.setBounds(270, 103, 26, 23);
		getContentPane().add(lblMatricularTodos);
		
		JLabel lblMatricularAluno = new JLabel(">");
		lblMatricularAluno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MatricularAluno();
			}
		});
		lblMatricularAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatricularAluno.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblMatricularAluno.setBounds(270, 137, 26, 23);
		getContentPane().add(lblMatricularAluno);
		
		JLabel lblDesmatricularAluno = new JLabel("<");
		lblDesmatricularAluno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DesmatricularALuno();
			}
		});
		lblDesmatricularAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesmatricularAluno.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDesmatricularAluno.setBounds(270, 175, 26, 23);
		getContentPane().add(lblDesmatricularAluno);
		
		JLabel lblDesmatricularTodos = new JLabel("<<");
		lblDesmatricularTodos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DesmatricularTodos();
			}
		});
		lblDesmatricularTodos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesmatricularTodos.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDesmatricularTodos.setBounds(270, 211, 26, 23);
		getContentPane().add(lblDesmatricularTodos);
		
		LoadTables();
	}

	protected void Save() {
		
		matriculaController.DesmatricularAlunos(matriculaController.getMatricula().getAlunosRemoverMatricula());
		matriculaController.MatricularAlunos(matriculaController.getMatricula().getAlunosMatriculados());

		MessageConfirm.informDialog("Matrícula dos alunos atualizada com sucesso!");
		
		dispose();
	}


	protected void DesmatricularTodos() {
		ArrayList<Aluno> lstAlunos = this.matriculaController.getMatricula().getAlunosMatriculados();
		
		for(Aluno aluno: lstAlunos) {
			this.matriculaController.getMatricula().desmatricularAluno(aluno);
		}
	}

	protected void DesmatricularALuno() {
		int index = tblAlunosMatricular.getSelectedRow();
		
		if(index == -1)
			return;
		
		Aluno aluno = getAt(tblAlunosMatricular, index);
		
		this.matriculaController.getMatricula().desmatricularAluno(aluno);
		LoadTables();		
	}

	protected void MatricularAluno() {
		int index = tblAlunos.getSelectedRow();
		
		if(index == -1)
			return;
		
		Aluno aluno = getAt(tblAlunos, index);
		
		this.matriculaController.getMatricula().matricularAluno(aluno);
		LoadTables();	
	}

	protected void MatricularTodos() {
		ArrayList<Aluno> lstAlunos = this.matriculaController.getMatricula().getAlunos();
		
		for(Aluno aluno: lstAlunos) {
			this.matriculaController.getMatricula().matricularAluno(aluno);
		}
		
		LoadTables();
	}

	private void LoadTables() {
		LoadAlunosMatriculados();
		LoadAlunos();
	}

	private void LoadAlunos() {
        var listaAlunos = this.matriculaController.getMatricula().getAlunos();
		DefaultTableModel model = (DefaultTableModel) this.tblAlunos.getModel();
		
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
	
	protected Aluno getAt(JTable tblContent, int index) {
		int id = (int) tblContent.getValueAt(index, tblContent.getColumn("Id").getModelIndex());
		String name = (String) tblContent.getValueAt(index, tblContent.getColumn("Aluno").getModelIndex());
		String email = (String) tblContent.getValueAt(index, tblContent.getColumn("Email").getModelIndex());
		String RA = (String) tblContent.getValueAt(index, tblContent.getColumn("RA").getModelIndex());
		return new Aluno(id, name, email, RA);
	}

	private void LoadAlunosMatriculados() {
        var listaAlunos = this.matriculaController.getMatricula().getAlunosMatriculados();
		DefaultTableModel model = (DefaultTableModel) this.tblAlunosMatricular.getModel();
		
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
