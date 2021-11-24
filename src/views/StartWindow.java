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

import shared.database.migrations.CreateProfessorTable;
import shared.database.seeders.AlunoSeeder;
import shared.database.seeders.ProfessorSeeder;
import shared.database.seeders.TerceiroSeeder;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
		
		JMenuBar menuBar = new JMenuBar();
		frmSistemaDeGerenciamento.setJMenuBar(menuBar);
		
		JMenu mnuGerenciamento = new JMenu("Gerenciamento");
		mnuGerenciamento.setMnemonic('g');
		menuBar.add(mnuGerenciamento);
		
		JMenuItem mnuAlunos = new JMenuItem("Alunos");
		mnuAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormAluno.main(null);
			}
		});
		mnuGerenciamento.add(mnuAlunos);
		
		JMenuItem mnuProfessores = new JMenuItem("Professores");
		mnuProfessores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormProfessor.main(null);
			}
		});
		mnuGerenciamento.add(mnuProfessores);
		
		JMenuItem mnuTerceiros = new JMenuItem("Terceiros");
		mnuTerceiros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTerceiro.main(null);
			}
		});
		mnuGerenciamento.add(mnuTerceiros);
		
		JMenuItem mnuTurmas = new JMenuItem("Turmas");
		mnuTurmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTurma.main(null);
			}
		});
		mnuGerenciamento.add(mnuTurmas);
		
		JMenu mnuAdministrativo = new JMenu("Administrativo");
		mnuAdministrativo.setMnemonic('a');
		menuBar.add(mnuAdministrativo);
		
		JMenuItem mnuDisciplinas = new JMenuItem("Disciplinas");
		mnuDisciplinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormDisciplina.main(null);
			}
		});
		mnuAdministrativo.add(mnuDisciplinas);
		
		JMenuItem mnuFuncoes = new JMenuItem("Fun\u00E7\u00F5es");
		mnuFuncoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormFuncoes.main(null);
			}
		});
		mnuAdministrativo.add(mnuFuncoes);
		
		JMenu mnuRelatorios = new JMenu("Relat\u00F3rios");
		mnuRelatorios.setMnemonic('r');
		menuBar.add(mnuRelatorios);
		
		JMenuItem mnuRelatorioAlunos = new JMenuItem("Relat\u00F3rio de Alunos");
		mnuRelatorios.add(mnuRelatorioAlunos);
		
		JMenuItem mnuRelatorioTurmas = new JMenuItem("Relat\u00F3rio de Turmas");
		mnuRelatorios.add(mnuRelatorioTurmas);
		
		JMenuItem mnuRelatorioDisciplinas = new JMenuItem("Relat\u00F3rio de Disciplinas");
		mnuRelatorios.add(mnuRelatorioDisciplinas);
	}
}
