package views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import java.awt.Dimension;

public class StartWindow {

	private JFrame frmSistemaDeGerenciamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
					window.frmSistemaDeGerenciamento.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDeGerenciamento = new JFrame();
		frmSistemaDeGerenciamento.setPreferredSize(new Dimension(450, 300));
		frmSistemaDeGerenciamento.setMinimumSize(new Dimension(450, 300));
		frmSistemaDeGerenciamento.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Estudos\\UTFPR\\7\u00BA Semestre\\Manuten\u00E7\u00E3o de Software\\Projetos\\SistemaEscolarRefatorar\\src\\shared\\icons\\estudando.png"));
		frmSistemaDeGerenciamento.setTitle("Sistema de Gerenciamento Escolar");
		frmSistemaDeGerenciamento.setBounds(100, 100, 450, 300);
		frmSistemaDeGerenciamento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frmSistemaDeGerenciamento.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JButton btnAluno = new JButton("Aluno");
		btnAluno.setBounds(50, 47, 92, 31);
		layeredPane.add(btnAluno);
		
		JButton btnProfessor = new JButton("Professor");
		btnProfessor.setBounds(50, 102, 92, 31);
		layeredPane.add(btnProfessor);
		
		JButton btnTerceiro = new JButton("Terceiro");
		btnTerceiro.setBounds(50, 156, 92, 31);
		layeredPane.add(btnTerceiro);
		
		JButton btnTurma = new JButton("Turma");
		btnTurma.setBounds(278, 47, 92, 31);
		layeredPane.add(btnTurma);
		
		JButton btnRelatorio = new JButton("Relat\u00F3rio");
		btnRelatorio.setBounds(278, 102, 92, 31);
		layeredPane.add(btnRelatorio);
	}
}
