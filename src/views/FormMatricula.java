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

import java.awt.Dimension;

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
				"Aluno", "Email", "RA"
			}
		));
		tblAlunos.getColumnModel().getColumn(0).setPreferredWidth(119);
		tblAlunos.getColumnModel().getColumn(1).setPreferredWidth(170);
		tblAlunos.getColumnModel().getColumn(2).setPreferredWidth(108);
		tblAlunos.setBounds(10, 39, 250, 271);
		getContentPane().add(tblAlunos);
		
		tblAlunosMatricular = new JTable();
		tblAlunosMatricular.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Aluno", "Email", "RA"
				}
			));
		tblAlunosMatricular.getColumnModel().getColumn(0).setPreferredWidth(119);
		tblAlunosMatricular.getColumnModel().getColumn(1).setPreferredWidth(170);
		tblAlunosMatricular.getColumnModel().getColumn(2).setPreferredWidth(108);
		tblAlunosMatricular.setBounds(301, 39, 250, 271);
		getContentPane().add(tblAlunosMatricular);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(431, 342, 120, 30);
		getContentPane().add(btnNewButton);
		
		JButton btnGravar = new JButton("Gravar");
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
		lblMatricularTodos.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblMatricularTodos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatricularTodos.setBounds(270, 103, 26, 23);
		getContentPane().add(lblMatricularTodos);
		
		JLabel lblMatricularAluno = new JLabel(">");
		lblMatricularAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatricularAluno.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblMatricularAluno.setBounds(270, 137, 26, 23);
		getContentPane().add(lblMatricularAluno);
		
		JLabel lblDesmatricularAluno = new JLabel("<");
		lblDesmatricularAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesmatricularAluno.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDesmatricularAluno.setBounds(270, 175, 26, 23);
		getContentPane().add(lblDesmatricularAluno);
		
		JLabel lblDesmatricularTodos = new JLabel("<<");
		lblDesmatricularTodos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesmatricularTodos.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDesmatricularTodos.setBounds(270, 211, 26, 23);
		getContentPane().add(lblDesmatricularTodos);
		
		LoadAlunosMatriculados();
		LoadAlunos();
	}

	private void LoadAlunos() {
        var listaAlunos = this.matriculaController.getMatricula().getAlunosMatriculados();
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
