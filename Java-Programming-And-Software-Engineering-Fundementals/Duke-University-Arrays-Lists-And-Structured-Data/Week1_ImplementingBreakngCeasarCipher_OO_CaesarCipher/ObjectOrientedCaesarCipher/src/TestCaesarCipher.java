import edu.duke.*;
import java.util.*;
/**
 * This class tests examples that use the CaesarCipher class, including writing a
 * method that will automatically decrypt an encrypted file by determining the key
 * and then decrypting with that same key.
 */
public class TestCaesarCipher
{

    /**
     * This method coounts the occurrences of letters in the String encrypted.
     * @param encrypted is the String for which the occurrences of letters need to
     * be calculated.
     * @return ann Array of integers with frequencies of letters in it.
     */
    private int[] countLetters(String encrypted)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];
        for (int k = 0; k < encrypted.length(); k++)
        {
            char currentChar = encrypted.charAt(k);
            currentChar = Character.toLowerCase(currentChar);
            int indexOfCurrentChar = alphabet.indexOf(currentChar);
                if(indexOfCurrentChar != -1)
                {
                    counters[indexOfCurrentChar]++;
                }
        }
        return counters;
    }

    /**
     * Find the index with the largest value in the array.
     * @param values Array of itegers to be checked.
     * @return the index with largest value.
     */
    private int maxIndex(int[] values)
    {
        int maxOfIndex = 0;
        for (int k = 0; k < values.length; k++)
        {
            if (values[k] > values[maxOfIndex])
            {
                maxOfIndex = k;
            }
        }
        return maxOfIndex;
    }

    /**
     * This method reads in a file as String, creates a CaesarCipher object with
     * with a given a key, encrypts the String read in using the CaesarCipher object,
     * prints the encrypted String and decrypt the encrypted String using the decrypt method.
     */
    private void simpleTests()
    {
        FileResource file = new FileResource();
        String message = file.asString();

        CaesarCipher cipher = new CaesarCipher(15);

        System.out.println("Encrypting with the encrypt() Method");
        System.out.println("=====================================");
        String encrypt = cipher.encrypt(message);
        System.out.println("Encrypted message: " + encrypt);
        System.out.println();

        System.out.println("Decrypting with the decrypt() Method");
        System.out.println("======================================");
        String decrypt = cipher.decrypt(encrypt);
        System.out.println("Decrypted message: " + decrypt);
        System.out.println();

        System.out.println("Decrypting with the breakCaesarCipher() Method: ");
        System.out.println("===============================================");
        String breaker = breakCaesarCipher(encrypt);
        System.out.println("Caesar Cipher Breaker Decrypts: " + breaker);
    }

    /**
     * Thia method should figure out which key was used to encrypt this message
     * (in a simular manner as the previous lesson), then create a CaesarCipher object
     * with that key and decrypt the message.
     * @param input is the encrypted message for which the we will figure out the key used
     * @return the decrypted message.
     */
    private String breakCaesarCipher(String input)
    {
        int[] frequencies = countLetters(input);
        int maxOfIndex = maxIndex(frequencies);
        int encryptKey = maxOfIndex - 4;

        if(maxOfIndex < 4)
        {
            encryptKey = 26 - (4 - maxOfIndex);
        }
        CaesarCipher cipher = new CaesarCipher(encryptKey);
        return cipher.decrypt(input);
    }

    public static void main (String[] args)
    {
        TestCaesarCipher testCipher = new TestCaesarCipher();
        testCipher.simpleTests();
    }
}
