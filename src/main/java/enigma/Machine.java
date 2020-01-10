package enigma;

public class Machine {

	private Rotor leftRotor;
	private Rotor middleRotor;
	private Rotor rightRotor;
	private Reflector reflector;

	/**
	 * Initialize the instance's rotor attributes
	 *
	 * @param reflector
	 * @param left
	 * @param middle
	 * @param right
	 */
	public void initRotors(Reflector reflector, Rotor left, Rotor middle, Rotor right) {
		this.reflector = reflector;
		leftRotor = left;
		middleRotor = middle;
		rightRotor = right;
	}

	/**
	 * Initialize rotors and relfectors positions from the setting string
	 *
	 * @param setting
	 */
	public void setPositions(String setting) {
		char[] charSettings = setting.toCharArray();
		reflector.setPosition(Rotor.toIndex(charSettings[0]));
		leftRotor.setPosition(Rotor.toIndex(charSettings[1]));
		middleRotor.setPosition(Rotor.toIndex(charSettings[2]));
		rightRotor.setPosition(Rotor.toIndex(charSettings[3]));
	}

	/**
	 * Launch the previous methods the intialize the machine's rotors and apply the setting string
	 *
	 * @param reflector
	 * @param left
	 * @param middle
	 * @param right
	 * @param setting
	 */
	public void configure(Reflector reflector, Rotor left, Rotor middle, Rotor right, String setting) {
		this.initRotors(reflector, left, middle, right);
		this.setPositions(setting);
	}

	/**
	 * Convert a string with Enigma
	 *
	 * @param msg
	 * @return
	 */
	public String convert(String msg) {
		msg = msg.toUpperCase();
		char[] msgChars = msg.toCharArray();
		String result = "";
		for (char c : msgChars) {
			result += convertChar(c);
		}

		return result;
	}

	/**
	 * Convert a char by passing it thought the 3 rotors forward, the reflector, and back again in the 3 rotors backward
	 *
	 * @param c
	 * @return
	 */
	char convertChar(char c) {
		advanceRotors();
		int charIndex = Rotor.toIndex(c);
		int output;
		output = rightRotor.convertForward(charIndex);
		output = middleRotor.convertForward(output);
		output = leftRotor.convertForward(output);
		output = reflector.convertForward(output);
		output = leftRotor.convertBackward(output);
		output = middleRotor.convertBackward(output);
		output = rightRotor.convertBackward(output);
		return Rotor.toLetter(output);
	}

	void advanceRotors() {
		boolean advanceLeft = false;
		boolean advanceMiddle = false;
		boolean advanceRight = true;
		if (leftRotor.atNotch()) {
		}
		if (middleRotor.atNotch()) {
			advanceMiddle = true;
			advanceLeft = true;
		}
		if (rightRotor.atNotch()) {
			advanceMiddle = true;
			advanceRight = true;
		}
		if (advanceLeft) {
			leftRotor.advance();
		}
		if (advanceRight) {
			rightRotor.advance();
		}
		if (advanceMiddle) {
			middleRotor.advance();
		}
	}
}
