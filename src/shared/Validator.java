/**
 * 
 */
package shared;

/**
 * @author enio
 *
 */
public class Validator {

	public static boolean ValidName(String name) {
		return !name.isBlank() || !name.isEmpty();
	}
	
	public static boolean ValidEmail(String email) {
		return ValidName(email) || email.matches("@");
	}
	
	public static boolean ValidNumber(String number) {
		return !number.isBlank() || !number.isEmpty() || number.matches("[0-9]");
	}
}
