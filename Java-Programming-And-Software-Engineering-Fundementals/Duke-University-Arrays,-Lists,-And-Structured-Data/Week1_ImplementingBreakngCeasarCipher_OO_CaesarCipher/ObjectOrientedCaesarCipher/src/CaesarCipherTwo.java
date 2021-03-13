import edu.duke.*;

/**
 * In this assignment, you will put together the CaesarCipherTwo class
 * that encrypts a message with two keys (the same way as the previous lesson:
 * key1 is used to encrypt every other letter, starting with the first,
 * and key2 is used to encrypt every other letter, starting with the second),
 * and also decrypts the same message.
 */
public class CaesarCipherTwo
{
    /**
     * Declaration of the instance variables.
     */
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    /**
     * This method sould initialize all the private fields
     * @param key1 is used to encrypt every other character starting with the first.
     * @param key2 is used to encrypt every other character starting with the second.
     */
    public CaesarCipherTwo(int key1, int key2)
    {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1)
                + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2)
                + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    /**
     * This method returns a String that is the input encrypted using the two
     * shifted alphabets(Using key1 and key2).
     * @param input is the String to be encrypted.
     * @return a String that is encrypted using the two shifted alphabets.
     */
    public String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);

        String alphabetLower = alphabet.toLowerCase();
        String shiftedAlphabetLower1 = shiftedAlphabet1.toLowerCase();
        String shiftedAlphabetLower2 = shiftedAlphabet2.toLowerCase();

        for (int k = 0; k < encrypted.length() ; k++)
        {
            char currentChar = encrypted.charAt(k);
            int indexOfCurrentCharAlphabet = alphabet.indexOf(currentChar);
            int indexOfCurrentCharLower = alphabetLower.indexOf(currentChar);

            if (indexOfCurrentCharAlphabet != -1 && k % 2 == 0)
            {
                char newCurrentCharAlphabet = shiftedAlphabet1.charAt(indexOfCurrentCharAlphabet);
                encrypted.setCharAt(k, newCurrentCharAlphabet);
            }

            if (indexOfCurrentCharAlphabet != -1 && k % 2 != 0)
            {
                char newCurrentCharAlphabet = shiftedAlphabet2.charAt(indexOfCurrentCharAlphabet);
                encrypted.setCharAt(k, newCurrentCharAlphabet);
            }

            if (indexOfCurrentCharLower != -1 && k % 2 == 0)
            {
                char newCurrentCharLower = shiftedAlphabetLower1.charAt(indexOfCurrentCharLower);
                encrypted.setCharAt(k, newCurrentCharLower);
            }

            if (indexOfCurrentCharLower != -1 && k % 2 != 0)
            {
                char newCurrentCharLower = shiftedAlphabetLower2.charAt(indexOfCurrentCharLower);
                encrypted.setCharAt(k, newCurrentCharLower);
            }
        }
        return encrypted.toString();
    }

    /**
     * This method returns a String that is the encrypted String decrypted using key1
     * amd key2 associated with this CaesarCipherTwo object.
     * @param input is the String to be decrypted.
     * @return the decrypted String.
     */
    public String decrypt(String input)
    {
        CaesarCipherTwo cipher = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cipher.encrypt(input);
    }

    public static void main(String[] args)
    {
//        FileResource file = new FileResource();
//        String message = file.asString();

        CaesarCipherTwo cipher = new CaesarCipherTwo( 14, 24);
        //String encrypt = cipher.encrypt(message);
        //System.out.println("Encrypted message: " + encrypt);
        System.out.println();
        String decrypt = cipher.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy.");
        System.out.println("Decrypted message: " + decrypt);

    }
}
