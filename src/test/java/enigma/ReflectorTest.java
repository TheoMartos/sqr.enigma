package enigma;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReflectorTest {
	Reflector testReflector = Reflector.reflectorFactory("Y R U H Q S L D P X N G O K M I E B F Z C W V J A T");

	@Test
	public void relfector_convert_forward()
	{
		int indexToTest = 0; // Math the letter A in the standard alpha
		int actual = testReflector.convertForward(indexToTest);
		int expected = 24; // Math the letter A in the cypher
		assertEquals(expected, actual);
	}
}
