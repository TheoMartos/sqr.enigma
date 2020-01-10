package enigma;

public class Rotor {

    private int position;
    private int[] cipher = new int[26];
    private int[] bcipher = new int[26];
    private int notch1 = -1;
    private int notch2 = -1;

	/**
	 * Reader for the position
	 *
	 * @return position
	 */
	public int getPosition() {
        return position;
    }

	/**
	 * Writter for the position
	 * @param posn the new position
	 */
	public void setPosition(int posn) {
        position = posn;
    }

	/**
	 * Static method to create a rotor
	 *
	 * @param str
	 * @param notches
	 * @return the created Rotor instance
	 */
	public static Rotor rotorFactory(String str, String notches) {
		char[] s = str.trim().replace(" ", "").toCharArray();
		int[] cipher = new int[26];
		for (int i = 0; i < 26; i++){
			cipher[i] = toIndex(s[i]);
		}
		s = notches.trim().replace(" and ", "").toCharArray();
		if (s.length == 2) {
			return new Rotor(cipher, toIndex(s[0]), toIndex(s[1]));
		} else {
			return new Rotor(cipher, toIndex(s[0]));
		}
	}
	
	private Rotor(int[] c, int notch1, int notch2) {
		this.notch1 = notch1;
		this.notch2 = notch2;
		cipher = c;
		createBCipher();
	}
	
	private Rotor(int[] c, int notch1) {
		this.notch1 = notch1;
		cipher = c;
		createBCipher();
	}
	
	protected Rotor() {
		
	}

	/**
	 * Method that convert this the forward cipher
	 *
	 * @param p
	 * @return
	 */
    public int convertForward(int p) {
        return ((cipher[((p + position) % 26 + 26) % 26] - position) % 26 + 26) % 26;
    }

	/**
	 * Method that convert this the backward cipher
	 *
	 * @param p
	 * @return
	 */
    public int convertBackward(int e) {
        return ((bcipher[((e + position) % 26 + 26) % 26] - position) % 26 + 26) % 26;
    }

	/**
	 * Method that move the rotor by 1 (the position has to be % 26)
	 */
	public void advance() {
        position = (position + 1) % 26;
    }
    
    protected boolean atNotch() {
        return (position == notch1 || position == notch2);
    }

    protected static char toLetter(int p) {
        return (char)(p + 'A');
    }

    protected static int toIndex(char c) {
        return c - 'A';
    }
    
	private void createBCipher() {
		for(int i = 0; i < 26; i++)
			bcipher[cipher[i]] = i;
	}
}
