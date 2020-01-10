package enigma;

import static org.junit.Assert.*;

import org.junit.Test;

public class RotorTest {

	Rotor testRotor = Rotor.rotorFactory("E K M F L G D Q V Z N T O W Y H X U S P A I B R C J", "Q");

	@Test
	public void rotor_convert_forward()
	{
		int indexToTest = 0; // Math the letter A in the standard alpha
		int actual = testRotor.convertForward(indexToTest);
		int expected = 4; // Math the letter E in the cypher
		assertEquals(expected, actual);
	}

	@Test
	public void rotor_convert_backward()
	{
		int indexToTest = 1; // Math the letter K in the standard alpha
		int actual = testRotor.convertForward(indexToTest);
		int expected = 10; // Math the letter N in the cypher
		assertEquals(expected, actual);
	}

	@Test
	public void rotor_advance_1() {
		testRotor.setPosition(1);
		testRotor.advance();
		int actual = testRotor.getPosition();
		int expected = 2;
		assertEquals(expected, actual);
	}

	@Test
	public void rotor_advance_limit() {
		testRotor.setPosition(25);
		testRotor.advance();
		int actual = testRotor.getPosition();
		int expected = 0;
		assertEquals(expected, actual);
	}
}
