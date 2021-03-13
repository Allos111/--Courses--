package com.willmayala;

import edu.duke.*;

import java.util.Arrays;

public class CaesarCipher
{
    /**
     * This method return a String which is the encypted version of the String input
     * The String input is encrypted by shifting the alphabet using int key(which is taken as a parameter)
     *
     * @param input message to be encrypted
     * @param key   is the key used to shift the alphabet to encrypt the message
     * @return the encrypted version of the String input. The string inside of the String Builder.
     */
    String encrypt(String input, int key)
    {
        //Initialization
        StringBuilder encrypted = new StringBuilder(input);

        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        //System.out.println("Alphabet: " + upperAlphabet);

        String shiftedUpperAlphabet = upperAlphabet.substring(key) +
                upperAlphabet.substring(0, key);
        System.out.println("Shifted Upper Alphabet: " + shiftedUpperAlphabet);
        String shiftedLowerAlphabet = lowerAlphabet.substring(key) +
                lowerAlphabet.substring(0, key);
        System.out.println("Shifted Lower Alphabet: " + shiftedLowerAlphabet);

        //count from 0 < length of encrypted
        for (int k = 0; k < encrypted.length(); k++) {
            //Find the character at index k and call it currentChar
            char currentChar = encrypted.charAt(k);
            //Find the index of currentChar and call it currentCharIndex
            int currentCharIndexUpper = upperAlphabet.indexOf(currentChar);
            int currentCharIndexLower = lowerAlphabet.indexOf(currentChar);

            if (currentCharIndexUpper != -1)//if the index is found
            {
                //Get the character found at the same index in the shifted alphabet and call it newChar
                char newCharUpper = shiftedUpperAlphabet.charAt(currentCharIndexUpper);
                //Replace that character in encrypted with newChar
                encrypted.setCharAt(k, newCharUpper);
            }

            if (currentCharIndexLower != -1) {
                char newCharLower = shiftedLowerAlphabet.charAt(currentCharIndexLower);
                encrypted.setCharAt(k, newCharLower);
            }
        }
        //The string inside encrypted is your answer
        return encrypted.toString();
    }

    /**
     * This method returns a String that has been encrypted using two keys.
     * Key1 is used to encrypt any other character with the Ceasar Cipher algorithm and key2
     * is used to encrypt any other character starting with the second character.
     *
     * @param input is String to encrypt
     * @param key1  is an int used to encrypt
     * @param key2  is an int used to encrypt
     * @return the encrypted String
     */
    String encryptTwoKeys(String input, int key1, int key2)
    {
        //This method returns a String that has been encrypted. Key1 is used to encrypt every other character with Ceaser Cipher algorithm
        //Starting with the first character and key2 is used to encrypt every other character, starting with the second character
        //Initialization

        StringBuilder encrypted = new StringBuilder(input);

        String upperAlphabet1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String upperAlphabet2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet1 = "abcdefghijklmnopqrstuvwxyz";
        String lowerAlphabet2 = "abcdefghijklmnopqrstuvwxyz";

        String shiftedUpperAlphabet1 = upperAlphabet1.substring(key1) +
                upperAlphabet1.substring(0, key1);
        String shiftedUpperAlphabet2 = upperAlphabet2.substring(key2) +
                upperAlphabet2.substring(0, key2);
        String shiftedLowerAlphabet1 = lowerAlphabet1.substring(key1) +
                lowerAlphabet1.substring(0, key1);
        String shiftedLowerAlphabet2 = lowerAlphabet2.substring(key2) +
                lowerAlphabet2.substring(0, key2);

        for (int k = 0; k < encrypted.length(); k++)
        {
            char currentChar = encrypted.charAt(k);
            int currentCharIndexUpper1 = upperAlphabet1.indexOf(currentChar);
            int currentCharIndexLower1 = lowerAlphabet1.indexOf(currentChar);
            int currentCharIndexUpper2 = upperAlphabet2.indexOf(currentChar);
            int currentCharIndexLower2 = lowerAlphabet2.indexOf(currentChar);

            if (currentCharIndexUpper1 != -1 && k % 2 == 0) {
                char newCharUpper1 = shiftedUpperAlphabet1.charAt(currentCharIndexUpper1);
                encrypted.setCharAt(k, newCharUpper1);
            }

            if (currentCharIndexLower1 != -1 && k % 2 == 0) {
                char newCharLower1 = shiftedLowerAlphabet1.charAt(currentCharIndexLower1);
                encrypted.setCharAt(k, newCharLower1);
            }

            if (currentCharIndexUpper2 != -1 && k % 2 != 0) {
                char newCharUpper2 = shiftedUpperAlphabet2.charAt(currentCharIndexUpper2);
                encrypted.setCharAt(k, newCharUpper2);
            }
            if (currentCharIndexLower2 != -1 && k % 2 != 0) {
                char newCharLower2 = shiftedLowerAlphabet2.charAt(currentCharIndexLower2);
                encrypted.setCharAt(k, newCharLower2);
            }
        }
        return encrypted.toString();
    }

    /**
     * Using brut force to decrypt an encrypted message with an unkown key
     * This method takes an encrypted String as parameter and returns
     * the decrypted version of it
     *
     * @param encrypted message to decrypt
     */
    private void eyeballDecrypt(String encrypted) {
        //Understanding brute force. This code allows us to try all the 26 keys available to break the Ceaser Cipher
        CaesarCipher cipher = new CaesarCipher();

        for (int k = 0; k < 26; k++) {
            String s = cipher.encrypt(encrypted, k); //Calling the encrypt method
            System.out.println(k + "\t" + s);
        }
    }

    private int[] countLetters(String encrypted) {
        //This method is the implementation of Cracking the Caesar Cipher by using the frequencies of letters in English text.
        //This method will count the occurrencies of every character in the string encrypted
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];

        for (int k = 0; k < encrypted.length(); k++) {
            char currentCh = encrypted.charAt(k);
            currentCh = Character.toLowerCase(currentCh);
            int indexOfCurrentCh = alphabet.indexOf(currentCh);
            if (indexOfCurrentCh != -1) {
                counters[indexOfCurrentCh]++;
            }
        }
        return counters;
    }

    private int maxIndex(int[] vals) {
        //This method will return the largest index, the location we will assume is where 'e' was shifted
        //the shifted alphabet is obtained using the position of 'e'
        int maxIndex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxIndex]) {
                maxIndex = k;
            }
        }
        System.out.println("the largest index: " + maxIndex);
        return maxIndex;
    }

    private String decrypt(String encrypted) {
        CaesarCipher cipher = new CaesarCipher();
        //Any frequency has a relationship between index and value
        //this array stores the occurrences of every character from the String encrypted
        int[] frequencies = countLetters(encrypted);
        //For my own understanding
        System.out.println("Frequencies: " + Arrays.toString(frequencies));
        //the frequencies are the occurrences of each character
        int maxIndex = maxIndex(frequencies);
        System.out.println("the largest index: " + maxIndex);
        int dKey = maxIndex - 4;
        System.out.println("dkey: " + dKey);
        if (maxIndex < 4) {
            int dkeyUpdated = 4 - maxIndex;
            dKey = 26 - dkeyUpdated;
            System.out.println("dkey updated: " + dKey);
        }
        return cipher.encrypt(encrypted, 26 - dKey);
    }

    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher();

//        int key1 = 21;
//        int key2 = 8;
//
//        FileResource fr = new FileResource();
//        String message = fr.asString();

        System.out.println("encrypt() Method");
        System.out.println("-----------------");
        System.out.println("Encrypted Message: ");
        System.out.println("====================");
        String encrypted = cipher.encrypt("FREE", 15);
        System.out.println("Encrypted message: " + encrypted);
        System.out.println("Decrypted message: ");
        System.out.println("==================");
        String decrypted = cipher.encrypt(encrypted, 26 - 15);
        System.out.println("Decrypted message: " + decrypted);

//        System.out.println("encryptTwoKeys() Method");
//        System.out.println("-----------------------");
//        System.out.println("Encrypted Message: ");
//        System.out.println("==================");
//        String testEncrypted = cipher.encryptTwoKeys(message, key1, key2);
////        System.out.println("Encrypted message: " + testEncrypted);
//        System.out.println("Decrypted message: ");
//        System.out.println("==================");
//        String testDecrypted = cipher.encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy", 26 - 14, 26 - 24);
//        System.out.println("Decrypted message: " + testDecrypted);
//
//        System.out.println("eyeballDecrypt() Method");
//        System.out.println("------------------------");
        //cipher.eyeballDecrypt(message);

//        System.out.println("Decrypt() Method");
//        System.out.println("------------------");
        // String decrypt = cipher.decrypt(message);
        //System.out.println("Encrypted message: " + message);
        //System.out.println("Decrypted message: " + decrypt);

        //System.out.println("Printing countLetters:");
        //cipher.countLetters("Just a test string with lots of eeeeeeeeeeeeeeeees ");
    }

}
