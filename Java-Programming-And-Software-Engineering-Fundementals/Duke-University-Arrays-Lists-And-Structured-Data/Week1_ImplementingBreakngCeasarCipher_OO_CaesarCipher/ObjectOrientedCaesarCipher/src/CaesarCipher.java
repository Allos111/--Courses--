/**
 * Rewriting the Caesar Cipher with Encapsulation. We will write the Caesar Cipher
 * in a more Object Oriented way. We will make better use of the Object Oriented
 * nature of Java.
 */
import edu.duke.*;
import java.util.*;

public class CaesarCipher
{
    /**
     * Fields are special variables that live inside of an object and are
     * declared inside of the class instead of methods.
     * These are now data that are encapsulated in your object.
     */
    private String upperAlphabet; //field one
    private String lowerAlphabet; //filed two
    private String shiftedUpperAlphabet; //field three
    private String  shiftedLowerAlphabet; //filed four
    private int mainKey; //field four

    /**
     * This method which is the constructor will initialize all the private fields
     * of the class.
     * @param key is the key used to encrypt the message. It should be used
     *            to decrypt the message too.
     * The code inside the constructor is run to initialize an object when it is
     *            created using new.
     */
    public CaesarCipher(int key)
    {
        upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedUpperAlphabet = upperAlphabet.substring(key)
                + upperAlphabet.substring(0, key);
        shiftedLowerAlphabet = lowerAlphabet.substring(key) +
                lowerAlphabet.substring(0, key);
        mainKey = key;
    }

    /**
     * This method returns a String that is the input encrypted using shiftedAlphabet.
     * @param input is the message to be encrypted.
     * @return the String input encrypted.
     */
    public String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);

        for (int k = 0; k < encrypted.length(); k++)
        {
            char currentChar = encrypted.charAt(k);
            int indexOfCurrentCharUpper = upperAlphabet.indexOf(currentChar);
            int indexOfCurrentCharLower = lowerAlphabet.indexOf(currentChar);

            if(indexOfCurrentCharUpper != -1)
            {
                char newCurrentCharUpper = shiftedUpperAlphabet.charAt(indexOfCurrentCharUpper);
                encrypted.setCharAt(k, newCurrentCharUpper);
            }

            if (indexOfCurrentCharLower != -1)
            {
                char newCurrentCharLower = shiftedLowerAlphabet.charAt(indexOfCurrentCharLower);
                encrypted.setCharAt(k, newCurrentCharLower);
            }
        }//end of for loop
        return encrypted.toString();
    }//end of encrypt method

    /**
     * This method returns a String that is the encrypted String decypted using the key
     * associated with this CaesarCipher object
     * @param input the encrypted String.
     * @return the decrypted version of the String input.
     */
    public String decrypt(String input)
    {
        System.out.println("This is printed from the decrypt() method");
        CaesarCipher cipher = new CaesarCipher(26 - mainKey);
        return cipher.encrypt(input);
    }

//    public static void main (String[] args)
//    {
//        FileResource file = new FileResource();
//        String message = file.asString();
//
//        CaesarCipher cipher = new CaesarCipher(15);
//
//        System.out.println("Encrypt() Method");
//        System.out.println("=================");
//        String encrypted = cipher.encrypt(message);
//        System.out.println("Encrypted message: " + encrypted);
//        System.out.println();
//        System.out.println("Decrypted() Method");
//        System.out.println("===================");
//        String decrypted = cipher.decrypt(encrypted);
//        System.out.println("Decrypted message: " + decrypted);
//    }
}
