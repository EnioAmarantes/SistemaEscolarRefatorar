/**
 * 
 */
package shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

/**
 * @author enio
 *
 */
class NumberValidatorTest {

	/**
	 * Test method for {@link shared.NumberValidator#insertString(int, java.lang.String, javax.swing.text.AttributeSet)}.
	 * @throws BadLocationException 
	 */
	JTextField txtRa;
	NumberValidator nb;
	
	@BeforeEach
	public void beforeEachTest() {
		txtRa = new JTextField();
		nb = new NumberValidator();
		txtRa.setDocument(nb);
	}
	
	@Test
	public void testInsertStringLetters() throws BadLocationException {
		nb.insertString(0, "sd", null);
		assertEquals("", txtRa.getText());
		
	}
	
	@Test
	public void testInsertStringEmpty() throws BadLocationException {
		nb.insertString(0, "", null);
		assertEquals("", txtRa.getText());
		
	}
	
	@Test
	public void testInsertStringBlankSpace() throws BadLocationException {
		nb.insertString(0, "  ", null);
		assertEquals("", txtRa.getText());
	}
	
	@Test
	public void testInsertStringNull() {	
		assertThrows(NullPointerException.class, () -> {
			nb.insertString(0, null, null);
		});
	}
	
	@Test
	public void testInsertStringNumber() throws BadLocationException {
		nb.insertString(0, "123987", null);
		assertEquals("123987", txtRa.getText());
	}

}
