package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidatorTest {

	// Test ValidName
	@Test
	void testValidNameEmpty() {
		assertFalse(Validator.ValidName(""));
	}
	
	@Test
	void testValidNameBlankSpace() {
		assertFalse(Validator.ValidName("   "));
	}
	
	@Test
	void testValidNameNull() {
		assertFalse(Validator.ValidName(null));
	}
	
	@Test 
	void testValidName(){
		assertTrue(Validator.ValidName("Enio"));
	}
	
	

	// Test ValidEmail
	@Test
	void testValidEmailEmpty() {
		assertFalse(Validator.ValidName(""));
	}
	
	@Test
	void testValidEmailBlankSpace() {
		assertFalse(Validator.ValidName("   "));
	}
	
	@Test
	void testValidEmailNull() {
		assertFalse(Validator.ValidName(null));
	}
	
	@Test
	void testValidEmailWithoutArroba() {
		assertFalse(Validator.ValidEmail("teste.teste"));
	}

	@Test
	void testValidEmailCorrect() {
		assertTrue(Validator.ValidName("teste@teste.com"));
	}
	
	
	
	// Test ValidNumber
	@Test
	void testValidNumberEmpty() {
		assertFalse(Validator.ValidName(""));
	}
	
	@Test
	void testValidNumberBlankSpace() {
		assertFalse(Validator.ValidName("   "));
	}
	
	@Test
	void testValidNumberNull() {
		assertFalse(Validator.ValidNumber(null));
	}

	@Test
	void testValidNumberCharactersLettersInvalid() {
		assertFalse(Validator.ValidNumber("asd"));
	}
	
	@Test
	void testValidNumberCharactersSpecialInvalid() {
		assertFalse(Validator.ValidNumber("!@#%$"));
	}
	
	@Test
	void testValidNumberCorrect() {
		assertTrue(Validator.ValidName("123"));
	}
	
}
