import edu.duke.*;
import java.util.*;

/**
 * In addition you will create a second class, TestCaesarCipherTwo to test examples
 * that use the CaesarCipherTwo class, including writing a method that will
 * automatically decrypt an encrypted file by determining the two keys
 * that were used to encrypt it.
 */
public class TestCaesarCipherTwo
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
     * This method should return a new String that is every
     * other character from message starting with the start position.
     * @param message   string to be split in half
     * @param   start   starting point to begin splitting
     * @return  string containing every other letter from input
     */
    private String halfOfString(String message, int start)
    {
        StringBuilder halfString = new StringBuilder();

        for (int k = start; k < message.length() ; k += 2)
        {
            halfString.append(message.charAt(k));
        }
        return halfString.toString();
    }

    /**
     * This method should figure out which keys were used to encrypt this message.
     * Then create a CaesarCipherTwo object with those keys and decrypt the message.
     * @param encrypted is the String to be decrypted.
     * @return the decrypted String.
     */
    private String breakCaesarCipher(String encrypted)
    {
        String firstHalfEncrypted = halfOfString(encrypted, 0);
        String secondHalfEncrypted = halfOfString(encrypted, 1);

        int firstHalfKey = getKey(firstHalfEncrypted);
        int secondHalfKey = getKey(secondHalfEncrypted);

        CaesarCipherTwo cipherTwo = new CaesarCipherTwo(firstHalfKey, secondHalfKey);
        //for my own understanding
        System.out.println("This is printed from the breakCaesarCipher Method");
        System.out.println("First key:\t" + firstHalfKey + "\nSecond key:\t"
                + secondHalfKey);
        System.out.println();

        return cipherTwo.decrypt(encrypted);
    }

    /**
     * With no return value, this method should read in file as a String,
     * create a CaesarCipherTwo object with keys 17 and 3, encrypt the String
     * using the CaesarCipherTwo object, print the encrypted String, and decrypt
     * the encrypted String using the decrypt method.
     */
    private void simpleTests()
    {
        System.out.println();
        FileResource resource = new FileResource();
        String message = resource.asString();

        CaesarCipherTwo cipherTwo = new CaesarCipherTwo(21, 8);
        String encrypt = cipherTwo.encrypt(message);
        System.out.println("Encrypted Message/CaesarCipherTwo: " + encrypt);

        String decrypt = cipherTwo.decrypt(encrypt);
        System.out.println("Decrypted message/CaesarCipherTwo: " + decrypt);

        String theBreaker = breakCaesarCipher(encrypt);
        System.out.println("Decrypted message using caesar breaker: " + theBreaker);
    }

    /**
     * Method for finding encryption key, based on most common occurrences of letter
     * in encrypted msg, assuming that it represents 'e';
     * @param input is the encrypted string for which the key is to be found
     * @return  encryption key for msg
     */
    private int getKey(String input)
    {
        int[] frequencies = countLetters(input);
        int maxOfIndex = maxIndex(frequencies);
        int encryptKey = maxOfIndex - 4;

        if(maxOfIndex < 4)
        {
            encryptKey = 26 - (4 - maxOfIndex);
        }
        return 26 - encryptKey;
    }

    public static void main(String[] args)
    {
        TestCaesarCipherTwo cipherTwo = new TestCaesarCipherTwo();
        cipherTwo.simpleTests();
    }
}
