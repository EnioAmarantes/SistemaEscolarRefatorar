package views;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Font;

public class StartWindow {

	private JFrame frmSistemaDeGerenciamento;
	private final static String TITLE = "Sistema de Gerenciamento Escolar";

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
		frmSistemaDeGerenciamento.setMinimumSize(new Dimension(400, 300));
		frmSistemaDeGerenciamento.setIconImage(Toolkit.getDefaultToolkit().getImage(StartWindow.class.getResource("/shared/icons/estudando.png")));
		frmSistemaDeGerenciamento.setTitle(TITLE);
		frmSistemaDeGerenciamento.setBounds(100, 100, 450, 300);
		frmSistemaDeGerenciamento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmSistemaDeGerenciamento.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnAluno = new JButton("Aluno");
		btnAluno.setBounds(55, 66, 87, 26);
		panel.add(btnAluno);
		
		JButton btnTurma = new JButton("Turma");
		btnTurma.setBounds(283, 66, 92, 26);
		panel.add(btnTurma);
		
		JButton btnProfessor = new JButton("Professor");
		btnProfessor.setBounds(55, 97, 87, 26);
		panel.add(btnProfessor);
		
		JButton btnRelatorio = new JButton("Relat\u00F3rio");
		btnRelatorio.setBounds(283, 97, 92, 26);
		panel.add(btnRelatorio);
		
		JButton btnTerceiro = new JButton("Terceiro");
		btnTerceiro.setBounds(55, 128, 87, 31);
		panel.add(btnTerceiro);
		
		JLabel lblTitle = new JLabel(TITLE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setBounds(55, 21, 320, 14);
		panel.add(lblTitle);
	}
}
