package views;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.InputStream;

//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


import shared.ADatabase;
import shared.consts.Config;
import shared.database.MySqlDatabase;
import shared.database.migrations.CreateAlunoTable;
import shared.database.migrations.CreateDisciplinaTable;
import shared.database.migrations.CreateFuncaoTable;
import shared.database.migrations.CreateMatriculaTable;
import shared.database.migrations.CreateProfessorTable;
import shared.database.migrations.CreateTerceiroTable;
import shared.database.migrations.CreateTurmaTable;
import shared.database.seeders.AlunoSeeder;
import shared.database.seeders.DisciplinaSeeder;
import shared.database.seeders.FuncaoSeeder;
import shared.database.seeders.ProfessorSeeder;
import shared.database.seeders.TerceiroSeeder;




import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class StartWindow {

	public static final String RELATORIO_TURMA = "Relatorio_Turma.jasper";
	public static final String RELATORIO_ALUNO = "Relatorio_Aluno.jasper";
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
		
	public void report(String arq) {
		 JasperPrint impr;
		 String relatorio = System.getProperty("user.dir") + "/src/report/" + arq;
	        try {
	        	MySqlDatabase.init(Config.PATHDB);


	            impr = JasperFillManager.fillReport(relatorio, null, MySqlDatabase.getConnection());
	            JasperViewer.viewReport(impr, false);
		   
	        } catch (JRException e) {
	        	System.out.println("erro "+ e);
	        	e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}

	/**
	 * Create the application.
	 */
	public StartWindow() {
		CreateFuncaoTable cFuncao = new CreateFuncaoTable();
		cFuncao.up(new FuncaoSeeder());
		CreateDisciplinaTable cDisciplina = new CreateDisciplinaTable();
		cDisciplina.up(new DisciplinaSeeder());
		CreateAlunoTable ca = new CreateAlunoTable();
		ca.up(new AlunoSeeder());
		CreateProfessorTable cp = new CreateProfessorTable();
		cp.up(new ProfessorSeeder());
		CreateTerceiroTable ct = new CreateTerceiroTable();
		ct.up(new TerceiroSeeder());
		CreateTurmaTable cTurma = new CreateTurmaTable();
		cTurma.up();
		CreateMatriculaTable cMatricula = new CreateMatriculaTable();
		cMatricula.up();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDeGerenciamento = new JFrame();
		frmSistemaDeGerenciamento.setMinimumSize(new Dimension(600, 450));
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
		mnuRelatorioAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				report(RELATORIO_ALUNO);
			}
		});
		mnuRelatorios.add(mnuRelatorioAlunos);
		
		JMenuItem mnuRelatorioTurmas = new JMenuItem("Relat\u00F3rio de Turmas");
		mnuRelatorioTurmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				report(RELATORIO_TURMA);
				
			}
		});
		mnuRelatorios.add(mnuRelatorioTurmas);
	}
}
