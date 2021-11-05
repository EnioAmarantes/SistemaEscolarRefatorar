package shared;

import javax.swing.JOptionPane;
import java.awt.Component;

public class MessageConfirm {
	
	public static boolean confirmDialog(String msg){
        String messageQuestion = "Deseja realmente excluir a entrada: "+ msg + " ?";
        String messageConfirm = "Entrada "+ msg +" removida com sucesso";
        String title = "Confirmar Exclusão ?";            
        int reply = JOptionPane.showConfirmDialog(null, messageQuestion, title, JOptionPane.YES_NO_OPTION);
        confirm(null, messageConfirm, null);
        return reply == JOptionPane.YES_OPTION;
	}
	
	public static boolean confirmDialog(Component arg0, String messageToRemove, String titleRemove, String msgRemoved, String titleConfirm){          
        int reply = JOptionPane.showConfirmDialog(null, messageToRemove, titleRemove, JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.YES_OPTION) confirm(arg0, titleConfirm, msgRemoved);
        return reply == JOptionPane.YES_OPTION;
	}
	
	public static void informDialog(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	private static void confirm(Component arg0, String msgRemoved, String titleConfirm) {
		JOptionPane.showMessageDialog(arg0, msgRemoved, titleConfirm, JOptionPane.INFORMATION_MESSAGE);
	}
	

}
