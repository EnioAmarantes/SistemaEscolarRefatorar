package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.FuncaoController;
import models.Funcao;
import shared.EMode;
import shared.MessageConfirm;
import shared.Validator;
import shared.forms.FormBase;

public class FormFuncoes extends FormBase<Funcao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FuncaoController funcaoController = new FuncaoController();
	
	private static final String TITLE_CONFIRM = null;
	private static final String TITLE_REMOVE = null;
	private static final String NameError = "Verifique o Nome dessa Função";
	
	private int Id = 0;
	
	private JTextField txtFuncao;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFuncoes frame = new FormFuncoes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FormFuncoes() {
		
		this.controller = funcaoController;
		this.TITLE_ERROR = "Erro com os dados da Função";
		
		String[] alunoColumns = {"Id", "Nome"};
		this.setColumns(alunoColumns);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		setThisTitle("Cadastro de Disciplina");
		getContentPane().setLayout(null);
		
		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setBounds(10, 63, 360, 14);
		getContentPane().add(lblDisciplina);
		
		txtFuncao = new JTextField();
		lblDisciplina.setLabelFor(txtFuncao);
		txtFuncao.setBounds(10, 88, 360, 20);
		getContentPane().add(txtFuncao);
		txtFuncao.setColumns(10);
		
		LoadTable();
	}

	@Override
	public void BackHome() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Remove() {
		int index = tblContent.getSelectedRow();
		
		if(index == -1)
			return;
		
		Funcao funcao = getAt(index);		
		String msgRemoverFuncao = "Deseja Remover a disciplina " + funcao.getNome() + "?";
		String msgFuncaoRemoved = "Disciplina " + funcao.getNome() + " removida com sucesso!";
		if(MessageConfirm.confirmDialog(this,  msgRemoverFuncao, TITLE_REMOVE, msgFuncaoRemoved, TITLE_CONFIRM)) {
			funcaoController.Excluir(funcao);
			RefreshTable();
		}
	}

	@Override
	public boolean is_valid() {
		boolean is_valid = true;
		
		is_valid &= NomeIsValid();
		
		return is_valid;
	}

	private boolean NomeIsValid() {
		if(!Validator.ValidName(txtFuncao.getText())) {
			MessageError(NameError);
			txtFuncao.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	public void FillTable(DefaultTableModel model) {
        var listaFuncao = funcaoController.Lista();
        
        for (Funcao funcao : listaFuncao) {
        	
            model.addRow(new Object[]{
        		funcao.getId(),
        		funcao.getNome()
            });
        }
        
        listaFuncao.clear();
	}

	@Override
	protected Funcao fill() {
		return new Funcao(Id, txtFuncao.getText());
	}

	@Override
	protected void fill(Funcao funcao) {
		Id = funcao.getId();
		txtFuncao.setText(funcao.getNome());
	}

	@Override
	protected Funcao getAt(int index) {
		int id = (int) tblContent.getValueAt(index, tblContent.getColumn("Id").getModelIndex());
		String nome = (String) tblContent.getValueAt(index, tblContent.getColumn("Nome").getModelIndex());
		
		return new Funcao(id, nome);
	}

	@Override
	public void Clear() {
		state = EMode.New;
		Id = 0;
		txtFuncao.setText("");
	}

}
