package shared;

import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.BadLocationException;

public class NumberValidator extends PlainDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		super.insertString(offs, str.replaceAll("[^0-9]", ""), a);
	}
}
