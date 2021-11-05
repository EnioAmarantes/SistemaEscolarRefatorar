package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidatorTest {

	@Test
	void testValidNameEmpty() {
		assertFalse(Validator.ValidName(""));
	}
	
	@Test
	void testValidNameNull() {
		assertFalse(Validator.ValidName(null));
	}
	
	@Test 
	void testValidName(){
		assertTrue(Validator.ValidName("Enio"));
	}

	@Test
	void testValidEmail() {
		assertFalse(Validator.ValidName(""));
	}
	
	@Test
	void testValidEmailNull() {
		assertFalse(Validator.ValidName(null));
	}
	
	@Test
	void testValidEmailWithoutArroba() {
		assertFalse(Validator.ValidEmail("teste.teste"));
	}

}
