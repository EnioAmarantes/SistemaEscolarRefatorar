package shared.forms;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public abstract class FormBase extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final static String TITLE = "Title";
	private JTable tblContent;

	/**
	 * Create the frame.
	 */
	public FormBase() {
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(600, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Voltar");
		btnBack.setOpaque(false);
		btnBack.setBounds(9, 12, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblTitle = new JLabel(TITLE);
		lblTitle.setName("");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitle.setBounds(9, 46, 330, 37);
		contentPane.add(lblTitle);
		
		JButton btnNew = new JButton("Novo");
		btnNew.setPreferredSize(new Dimension(75, 23));
		btnNew.setBounds(9, 261, 75, 23);
		contentPane.add(btnNew);
		
		JButton btnEdit = new JButton("Editar");
		btnEdit.setPreferredSize(new Dimension(75, 23));
		btnEdit.setBounds(94, 261, 75, 23);
		contentPane.add(btnEdit);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(179, 261, 75, 23);
		contentPane.add(btnCancel);
		
		JButton btnClear = new JButton("Limpar");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setBounds(264, 261, 75, 23);
		contentPane.add(btnClear);
		
		JButton btnCreate = new JButton("Criar");
		btnCreate.setPreferredSize(new Dimension(75, 23));
		btnCreate.setBounds(52, 310, 75, 29);
		contentPane.add(btnCreate);
		
		JButton btnUpdate = new JButton("Atualizar");
		btnUpdate.setPreferredSize(new Dimension(75, 23));
		btnUpdate.setBounds(137, 310, 75, 29);
		contentPane.add(btnUpdate);
		
		JButton btnRemove = new JButton("Remover");
		btnRemove.setPreferredSize(new Dimension(75, 23));
		btnRemove.setBounds(222, 310, 75, 29);
		contentPane.add(btnRemove);
		
		tblContent = new JTable();
		tblContent.setBounds(349, 46, 325, 304);
		contentPane.add(tblContent);
	}
}
