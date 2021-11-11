package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.TerceiroController;
import models.Terceiro;
import shared.APessoa;
import shared.EMode;
import shared.MessageConfirm;
import shared.NumberValidator;
import shared.Validator;
import shared.forms.FormPessoaBase;

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

	private static final String TITLE_ERROR = "Erro com os dados do Terceiro";
	private static final String TITLE_CONFIRM = null;
	private static final String TITLE_REMOVE = null;
	
	private static final String NameError = "Verifique o Nome desse Terceiro";
	private static final String EmailError = "Verifique Email desse Terceiro";
	private static final String RaError = "Verifique a Função desse Terceiro";
	
	private int Id = 0;
	private String Funcao = "";
	private JTextField txtFunc;
	
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
		
		txtFunc = new JTextField();
		lblRa.setLabelFor(txtFunc);
		txtFunc.setBounds(10, 198, 369, 20);
		getContentPane().add(txtFunc);
		txtFunc.setColumns(10);


		LoadTable();
	}


	@Override
	public void BackHome() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Clear() {
		state = EMode.New;
		
		Id = 0;
		
		Name = "";
		txtName.setText(Name);

		Email = "";
		txtEmail.setText(Email);

		Funcao = "";
		txtFunc.setText(Funcao);
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
		String Funcao = (String) tblContent.getValueAt(index, tblContent.getColumn("Funcao").getModelIndex());
		return new Terceiro(id, name, email, Funcao);
	}
	
	public Terceiro fill() {
		Name = txtName.getText();
		Email = txtEmail.getText();
		Funcao = txtFunc.getText();
		
		return new Terceiro(Id, Name, Email, Funcao);
	}
	
	@Override
	protected void fill(APessoa model) {
		Terceiro terceiro = (Terceiro) model;
		Id = terceiro.getId();
		txtName.setText(terceiro.getNome());
		txtEmail.setText(terceiro.getEmail());
		txtFunc.setText(terceiro.getFuncao());
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
		
		if(!Validator.ValidEmail(txtEmail.getText())) {
			MessageError(EmailError);
			txtEmail.requestFocus();
			return false;
		}
		return true;
	}

	private boolean FuncIsValid() {
		
		if(!Validator.ValidName(txtFunc.getText())) {
			MessageError(RaError);
			txtFunc.requestFocus();
			return false;
		}
		return true;
	}
	
	public void MessageError(String message) {
		JOptionPane.showMessageDialog(this, message, TITLE_ERROR, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void FillTable(DefaultTableModel model) {
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
