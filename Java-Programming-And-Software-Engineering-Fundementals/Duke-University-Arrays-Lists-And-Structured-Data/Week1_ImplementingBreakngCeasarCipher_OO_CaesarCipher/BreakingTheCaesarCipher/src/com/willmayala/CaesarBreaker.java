package com.willmayala;

import edu.duke.*;
import java.util.*;

public class CaesarBreaker
{
    /**
     * Snipet from lecture
     * Count occurrences of letters in the String message.
     *
     * @param message String for which the occurrences  of letters need to be calculated
     * @return int array containing frequencies of letters
     */
    public int[] countOccurrencesOfLetters(String message) {
        String alphabetLowerCase = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];

        for (int k = 0; k < message.length(); k++) {
            char currentCh = message.charAt(k);
            int indexOfCurrentCh = alphabetLowerCase.indexOf(Character.toLowerCase(currentCh));

            if (indexOfCurrentCh != -1) {
                counters[indexOfCurrentCh]++;
            }
        }
        return counters;
    }

    /**
     * Find index with the largest value in array.
     *
     * @param values array of ints to be checked.
     * @return index of max value in ints array
     */
    private int maxIndex(int[] values) {
        int maxIndex = 0;

        for (int k = 0; k < values.length; k++) {
            if (values[k] > values[maxIndex]) {
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    /**
     * Crack the CeasarCipher using the statical letter frequencies of English text
     * This method takes an encrypted message as a parameter and returns a decrypted version of that String
     * The decryption is made possible by shifting back the alphabet using the index of 'e' and calling
     * the encrypt method from the Ceasar Cipher class.
     * This algorithm assume that 'e' is the most appearing character in English text
     *
     * @param encrypted String to be decrypted
     */
    private String decrypt(String encrypted) {
        CaesarCipher cipher = new CaesarCipher();
        int[] frequencies = countOccurrencesOfLetters(encrypted);
        int maxIndex = maxIndex(frequencies);
        System.out.println("Max index: " + maxIndex);
        int dKey = maxIndex - 4;

        if (maxIndex < 4) {
            dKey = 26 - (4 - maxIndex);
        }
        //decrypt the message using the key found by shifting the alphabet
        //from the largest index
        System.out.println(dKey);
        return cipher.encrypt(encrypted, 26 - dKey);
    }

    /**
     * Using brut force to decrypt an encrypted message with an unkown key
     * This method takes an encrypted String as parameter and returns
     * the decrypted version of it
     *
     * @param encrypted message to decrypt
     *                  IMPORTANT NOTE: this method is here for my own understanding purpose only
     */
    private void eyeballDecrypt(String encrypted) {
        CaesarCipher cipher = new CaesarCipher();
        for (int k = 0; k < 26; k++) {
            String input = cipher.encrypt(encrypted, k);
            System.out.println(k + "\t" + input);
        }
    }

    /**
     * This method should return a new String that is every other character from
     * message starting with the start position. For example, the call
     * halfOfString(“Qbkm Zgis”, 0) returns the String “Qk gs” and the call
     * halfOfString(“Qbkm Zgis”, 1) returns the String “bmZi”.
     * Be sure to test this method with a small example.
     *
     * @param message String to decrypt
     * @param start   the starting position
     */
    private String halfOfString(String message, int start)
    {
        StringBuilder halfString = new StringBuilder();
        for (int k = start; k < message.length(); k += 2)
        {
            halfString.append(message.charAt(k));
        }
        return halfString.toString();
    }

    /**
     *
     */
    private int getKey(String encrypted) {
        int[] letterFreqs = countOccurrencesOfLetters(encrypted);
        int maxDex = maxIndex(letterFreqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        System.out.println("dKey: " + dkey);
        return 26 - dkey;
    }

    /**
     *
     */
    private String decryptTwoKeys(String encrypted) {
        String firstHalfEncrypted = halfOfString(encrypted, 0);
        String secondHalfEncrypted = halfOfString(encrypted, 1);

        int firstHalfKey = getKey(firstHalfEncrypted);
        int secondHalfKey = getKey(secondHalfEncrypted);

        CaesarCipher cipher = new CaesarCipher();

        System.out.println("First key:\t" + firstHalfKey + "\nSecond key:\t"
                + secondHalfKey);
        System.out.println();

        return cipher.encryptTwoKeys(encrypted, firstHalfKey, secondHalfKey);
    }


    public static void main(String[] args) {
        CaesarBreaker theBreaker = new CaesarBreaker();
        FileResource file = new FileResource();
        String message = file.asString();

        System.out.println("decrypt() Method");
        System.out.println("==================");
        System.out.println("Encrypted message: " + message);
        String decrypt = theBreaker.decrypt(message);
        System.out.println();
        System.out.println("Decrypted message: " + decrypt);
        System.out.println();
//
//        System.out.println("eyeballDecrypt() Method");
//        System.out.println("=======================");
//        System.out.println("Encrypted message: " + message);
//        System.out.println("Decrypted message: ");
//        theBreaker.eyeballDecrypt(message);
//        System.out.println();

//        System.out.println("decryptTwoKeys() Method");
//        System.out.println("==========================");
//        System.out.println("Encrypted message: " + message);
//        String decryptTwoKeys = breaker.decryptTwoKeys(message);
//        System.out.println("Decrypted message: " + decryptTwoKeys);

    }
}
