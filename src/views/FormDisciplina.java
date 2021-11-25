/**
 * 
 */
package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.DisciplinaController;
import models.Disciplina;
import shared.EMode;
import shared.MessageConfirm;
import shared.Validator;
import shared.forms.FormBase;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author enio
 *
 */
public class FormDisciplina extends FormBase<Disciplina> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DisciplinaController disciplinaController = new DisciplinaController();
	
	private static final String TITLE_CONFIRM = null;
	private static final String TITLE_REMOVE = null;
	private static final String NameError = "Verifique o Nome dessa Disciplina";
	
	private int Id = 0;
	
	private JTextField txtDisciplina;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDisciplina frame = new FormDisciplina();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FormDisciplina() {
		
		this.controller = disciplinaController;
		this.TITLE_ERROR = "Erro com os dados da Disciplina";
		
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
		
		txtDisciplina = new JTextField();
		lblDisciplina.setLabelFor(txtDisciplina);
		txtDisciplina.setBounds(10, 88, 360, 20);
		getContentPane().add(txtDisciplina);
		txtDisciplina.setColumns(10);
		
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
		
		Disciplina disciplina = getAt(index);		
		String msgRemoverDisciplina = "Deseja Remover a disciplina " + disciplina.getNome() + "?";
		String msgDisciplinaRemoved = "Disciplina " + disciplina.getNome() + " removida com sucesso!";
		if(MessageConfirm.confirmDialog(this,  msgRemoverDisciplina, TITLE_REMOVE, msgDisciplinaRemoved, TITLE_CONFIRM)) {
			disciplinaController.Excluir(disciplina);
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
		if(!Validator.ValidName(txtDisciplina.getText())) {
			MessageError(NameError);
			txtDisciplina.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	public void FillTable(DefaultTableModel model) {
        var listaDisciplina = disciplinaController.Lista();
        
        for (Disciplina disciplina : listaDisciplina) {
        	
            model.addRow(new Object[]{
        		disciplina.getId(),
        		disciplina.getNome()
            });
        }
        
        listaDisciplina.clear();
	}

	@Override
	protected Disciplina fill() {
		return new Disciplina(Id, txtDisciplina.getText());
	}

	@Override
	protected void fill(Disciplina disciplina) {
		Id =disciplina.getId();
		txtDisciplina.setText(disciplina.getNome());
	}

	@Override
	protected Disciplina getAt(int index) {
		int id = (int) tblContent.getValueAt(index, tblContent.getColumn("Id").getModelIndex());
		String nome = (String) tblContent.getValueAt(index, tblContent.getColumn("Nome").getModelIndex());
		
		return new Disciplina(id, nome);
	}

	@Override
	public void Clear() {
		state = EMode.New;
		Id = 0;
		txtDisciplina.setText("");
	}
}
