package shared.forms;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import shared.IBase;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public abstract class FormBase extends JFrame implements IBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected String TITLE = "Title";
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
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BackHome();
			}
		});
		btnBack.setOpaque(false);
		btnBack.setBounds(9, 12, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblTitle = new JLabel();
		lblTitle.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				lblTitle.setText(TITLE);
			}
		});
		lblTitle.setLabelFor(contentPane);
		lblTitle.setName("lblTitle");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitle.setBounds(9, 46, 330, 37);
		contentPane.add(lblTitle);
		
		JButton btnNew = new JButton("Novo");
		btnNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				New();
			}
		});
		btnNew.setPreferredSize(new Dimension(75, 23));
		btnNew.setBounds(9, 261, 75, 23);
		contentPane.add(btnNew);
		
		JButton btnEdit = new JButton("Editar");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Edit();
			}
		});
		btnEdit.setPreferredSize(new Dimension(75, 23));
		btnEdit.setBounds(94, 261, 75, 23);
		contentPane.add(btnEdit);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cancel();
			}
		});
		btnCancel.setBounds(179, 261, 75, 23);
		contentPane.add(btnCancel);
		
		JButton btnClear = new JButton("Limpar");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Clear();
			}
		});
		btnClear.setBounds(264, 261, 75, 23);
		contentPane.add(btnClear);
		
		JButton btnCreate = new JButton("Criar");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Create();
			}
		});
		btnCreate.setPreferredSize(new Dimension(75, 23));
		btnCreate.setBounds(52, 310, 75, 29);
		contentPane.add(btnCreate);
		
		JButton btnUpdate = new JButton("Atualizar");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Update();
			}
		});
		btnUpdate.setPreferredSize(new Dimension(75, 23));
		btnUpdate.setBounds(137, 310, 75, 29);
		contentPane.add(btnUpdate);
		
		JButton btnRemove = new JButton("Remover");
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Remove();
			}
		});
		btnRemove.setPreferredSize(new Dimension(75, 23));
		btnRemove.setBounds(222, 310, 75, 29);
		contentPane.add(btnRemove);
		
		tblContent = new JTable();
		tblContent.setBounds(349, 46, 325, 304);
		contentPane.add(tblContent);
	}
}
