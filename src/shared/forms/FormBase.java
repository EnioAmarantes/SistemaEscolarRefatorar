package shared.forms;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import lombok.Setter;
import shared.AModel;
import shared.EMode;
import shared.IBase;
import shared.IDao;
import shared.ITable;
import shared.MessageConfirm;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

@SuppressWarnings("hiding")
public abstract class FormBase<AModel> extends JFrame implements IBase, ITable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String TITLE_ERROR;
	protected String ERROR_MESSAGE;
	protected JPanel contentPane;
	private String TITLE = "Title";
	protected JTable tblContent;
	protected JScrollPane scrollPane;
	protected EMode state;
	@Setter
	protected String[] columns;
	protected IDao controller;

	/**
	 * Create the frame.
	 */
	public FormBase() {
		state = EMode.New;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormBase.class.getResource("/shared/icons/estudando.png")));
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(600, 400));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Voltar");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setIcon(new ImageIcon(FormBase.class.getResource("/shared/icons/setap.png")));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BackHome();
			}
		});
		btnBack.setOpaque(false);
		btnBack.setBounds(9, 11, 114, 37);
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
		
		JButton btnNew = new JButton("Salvar");
		btnNew.setMinimumSize(new Dimension(80, 23));
		btnNew.setMaximumSize(new Dimension(80, 23));
		btnNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Save();
			}
		});
		btnNew.setPreferredSize(new Dimension(80, 23));
		btnNew.setBounds(10, 261, 90, 29);
		contentPane.add(btnNew);
		
		JButton btnEdit = new JButton("Editar");
		btnEdit.setMinimumSize(new Dimension(80, 23));
		btnEdit.setMaximumSize(new Dimension(80, 23));
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Edit();
			}
		});
		btnEdit.setPreferredSize(new Dimension(80, 23));
		btnEdit.setBounds(104, 261, 90, 29);
		contentPane.add(btnEdit);
		
		JButton btnClear = new JButton("Limpar");
		btnClear.setPreferredSize(new Dimension(80, 23));
		btnClear.setMinimumSize(new Dimension(80, 23));
		btnClear.setMaximumSize(new Dimension(80, 23));
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Clear();
			}
		});
		btnClear.setBounds(196, 261, 90, 29);
		contentPane.add(btnClear);
		
		JButton btnRemove = new JButton("Remover");
		btnRemove.setMinimumSize(new Dimension(80, 23));
		btnRemove.setMaximumSize(new Dimension(80, 23));
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Remove();
			}
		});
		btnRemove.setPreferredSize(new Dimension(80, 23));
		btnRemove.setBounds(289, 261, 90, 29);
		contentPane.add(btnRemove);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(389, 46, 285, 304);
		contentPane.add(scrollPane);
		
		tblContent = new JTable();
		scrollPane.setViewportView(tblContent);
		
		JButton btnRefresh = new JButton("Atualizar");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RefreshTable();
				MessageConfirm.informDialog("Tabela de Dados Atualizada com Sucesso!");
			}
		});
		btnRefresh.setBounds(389, 10, 285, 29);
		contentPane.add(btnRefresh);
	}

	protected void setThisTitle(String title) {
		setTitle(title);
		TITLE = title;
	}
	
	public void createTable(String[] columns) {
		Object[][] data = {};
		this.tblContent.setModel(new DefaultTableModel(data, columns));
	}
	
	public void LoadTable() {
		createTable(columns);
		this.scrollPane.setViewportView(this.tblContent);
		
		RefreshTable();
	}
	
	public void RefreshTable() {
		DefaultTableModel model = (DefaultTableModel) this.tblContent.getModel();
		
        model.setRowCount(0);
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        FillTable(model);
        
        state = EMode.New;
	}
	
	@Override
	public void Save() {
		if(state == EMode.New)
			Create();
		
		if(state == EMode.Edit)
			Update();
	}
	
	@Override
	public void Create() {
		if(is_valid()) {
			AModel model = fill();
			controller.Cria(model);
			RefreshTable();
			Clear();	
		}else {
			MessageError(ERROR_MESSAGE);			
		}
	}
	
	@Override
	public void Edit() {
		state = EMode.Edit;
		
		int index = tblContent.getSelectedRow();
		
		if(index == -1) {
			Clear();	
			return;
		}
		
		AModel model = getAt(index);
		
		fill(model);
	}
	
	@Override
	public void Update() {
		if(is_valid()) {
			AModel model = fill();
			controller.Modificar(model);
			RefreshTable();
			Clear();	
		}else {
			MessageError(this.ERROR_MESSAGE);			
		}
	}
	
	public void MessageError(String message) {
		JOptionPane.showMessageDialog(this, message, TITLE_ERROR, JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected abstract AModel fill();
	protected abstract void fill(AModel model);
	protected abstract AModel getAt(int index);
	public abstract void Clear();
}
