package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.DisciplinaController;
import controllers.FuncaoController;
import controllers.TerceiroController;
import models.Terceiro;
import models.Disciplina;
import models.Funcao;
import shared.APessoa;
import shared.EMode;
import shared.MessageConfirm;
import shared.Validator;
import shared.forms.FormPessoaBase;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class FormTerceiro extends FormPessoaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


//	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	
	private TerceiroController terceiroController = new TerceiroController();

	private static final String TITLE_CONFIRM = null;
	private static final String TITLE_REMOVE = null;
	
	private static final String NameError = "Verifique o Nome desse Terceiro";
	private static final String EmailError = "Verifique Email desse Terceiro";
	private static final String RaError = "Verifique a Função desse Terceiro";
	
	private int Id = 0;
	private Funcao Funcao;
	private JComboBox<Funcao> jcbFuncao;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTerceiro frame = new FormTerceiro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormTerceiro() {
		setSize(new Dimension(980, 400));
		this.scrollPane.setBounds(388, 47, 560, 300);
		this.btnRefresh.setSize(560, 23);
		
		this.controller = terceiroController;
		this.TITLE_ERROR = "Erro com os dados de Terceirizado";
		String[] terceiroColumns = {"Id", "Nome", "Email", "Funcao"};
		this.setColumns(terceiroColumns);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		setThisTitle("Cadastro de Terceiros");
		getContentPane().setLayout(null);

		JLabel lblRa = new JLabel("Função");
		lblRa.setBounds(10, 178, 46, 14);
		getContentPane().add(lblRa);
		
		jcbFuncao = new JComboBox();
		lblRa.setLabelFor(jcbFuncao);
		jcbFuncao.setBounds(10, 198, 369, 20);
		getContentPane().add(jcbFuncao);


		LoadFuncao();
		LoadTable();
	}


	@Override
	public void BackHome() {
		// TODO Auto-generated method stub

	}
	
	public void LoadFuncao() {
		FuncaoController funcaoController = new FuncaoController();
		
		Vector<Funcao> listaFuncao = new Vector<Funcao>();
		
		for(Funcao funcao : funcaoController.Lista()) {
			listaFuncao.add(funcao);
		}
		
		jcbFuncao.setModel(new DefaultComboBoxModel<Funcao>(listaFuncao));
			
	}

	@Override
	public void Clear() {
		state = EMode.New;
		
		Id = 0;
		
		Name = "";
		txtName.setText(Name);

		Email = "";
		txtEmail.setText(Email);

		Funcao = new Funcao("");
		jcbFuncao.setSelectedItem(0);
	}

	@Override
	public void Remove() {
		int index = tblContent.getSelectedRow();
		
		if(index == -1)
			return;
		
		Terceiro terceiro = getAt(index);		
		String msgRemoverTerceiro = "Deseja Remover o terceiro " + terceiro.getNome() + "?";
		String msgTerceiroRemoved = "Terceiro " + terceiro.getNome() + " removido com sucesso!";
		if(MessageConfirm.confirmDialog(this,  msgRemoverTerceiro, TITLE_REMOVE, msgTerceiroRemoved, TITLE_CONFIRM)) {
			terceiroController.Excluir(terceiro);
			RefreshTable();
		}
		
	}


	protected Terceiro getAt(int index) {
		int id = (int) tblContent.getValueAt(index, tblContent.getColumn("Id").getModelIndex());
		String name = (String) tblContent.getValueAt(index, tblContent.getColumn("Nome").getModelIndex());
		String email = (String) tblContent.getValueAt(index, tblContent.getColumn("Email").getModelIndex());
		Funcao Funcao = (Funcao) tblContent.getValueAt(index, tblContent.getColumn("Funcao").getModelIndex());
		return new Terceiro(id, name, email, Funcao);
	}
	
	public Terceiro fill() {
		Name = txtName.getText();
		Email = txtEmail.getText();
		Funcao = (Funcao) jcbFuncao.getSelectedItem();
		
		return new Terceiro(Id, Name, Email, Funcao);
	}
	
	@Override
	protected void fill(APessoa model) {
		Terceiro terceiro = (Terceiro) model;
		Id = terceiro.getId();
		txtName.setText(terceiro.getNome());
		txtEmail.setText(terceiro.getEmail());
		jcbFuncao.setSelectedItem(terceiro.getFuncao());
	}

	@Override
	public boolean is_valid() {
		// TODO Auto-generated method stub
		boolean isValid = true;
		
		isValid &= NameIsValid();
		
		isValid &= EmailIsValid();
		
		isValid &= FuncIsValid();
		
		return isValid;
	}
	private boolean NameIsValid() {
	
		if(!Validator.ValidName(txtName.getText())) {
			MessageError(NameError);
			txtName.requestFocus();
			return false;
		}
		return true;
	}


	private boolean EmailIsValid() {
		ArrayList<Terceiro> terceiros = controller.Lista();
		String email = txtEmail.getText();
		
		if(!Validator.ValidEmail(txtEmail.getText())) {
			MessageError(EmailError);
			txtEmail.requestFocus();
			return false;
		}
		
		for(Terceiro terceiro : terceiros) {
			if(terceiro.getEmail().equals(email)) {
				ERROR_MESSAGE = "O email " + email + " já está cadastrado";
				return false;
			}
		}
		return true;
	}

	private boolean FuncIsValid() {
		
		if(!Validator.ValidName(jcbFuncao.getSelectedItem().toString())) {
			MessageError(RaError);
			jcbFuncao.requestFocus();
			return false;
		}
		return true;
	}
	
	public void MessageError(String message) {
		JOptionPane.showMessageDialog(this, message, TITLE_ERROR, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void FillTable(DefaultTableModel model) {
		
		this.tblContent.getColumnModel().getColumn(0).setPreferredWidth(10);
		this.tblContent.getColumnModel().getColumn(3).setPreferredWidth(113);
		
        var listaTerceiros = terceiroController.Lista();
        
        for (Terceiro terceiro : listaTerceiros) {
        	
            model.addRow(new Object[]{
                terceiro.getId(),
                terceiro.getNome(),
                terceiro.getEmail(),
                terceiro.getFuncao()
            });
        }
        
        listaTerceiros.clear();
	}

}
