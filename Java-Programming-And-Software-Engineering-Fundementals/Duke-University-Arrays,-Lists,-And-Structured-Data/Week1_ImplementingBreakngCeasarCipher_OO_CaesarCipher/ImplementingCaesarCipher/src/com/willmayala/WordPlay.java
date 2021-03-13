package com.willmayala;

import edu.duke.*;
import java.util.*;


public class WordPlay
{
    /**
     * This method returns true if the char ch is a vowel(not case sensitive)
     *
     * @param ch is the character to check out. The input from the user.
     * @return a boolean if ch is a vowel(upper or lower case).
     */
    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' ||
                ch == 'i' || ch == 'I' || ch == 'o' || ch == 'O' || ch == 'u' || ch == 'U';
    }

    /**
     * This method takes a String phrase and a char ch as parameters
     * and returns a String with all the vowels in phrase replaced by the char ch
     *
     * @param phrase is the String to work on
     * @param ch     is the char that replaces all the vowels in phrase
     * @return a value which is String with all the vowels replaced by ch
     */
    private String replaceVowel(String phrase, char ch)
    {
        //Build a String using StringBuilder. This String is the one to be returned. A string with no vowels
        StringBuilder phraseWithNoVowels = new StringBuilder(phrase);

        System.out.println("Phrase with no vowels: " + phraseWithNoVowels);
        //Count from 0 < length of phrase
        for (int k = 0; k < phrase.length(); k++)
        {
            //Look at the kth character of phrase and store it in currentChar
            //In other words Look at the character at the kth position and store it in currentChar
            char currentChar = phrase.charAt(k);
            //if currentChar is a vowel
            if (isVowel(currentChar)) //This is a call to the method isVowel
            {
                //replace it by ch
                phraseWithNoVowels.setCharAt(k, ch);
            } else {
                //if not, replace it by itself
                phraseWithNoVowels.setCharAt(k, currentChar);
            }
        }
        //The String inside phraseWithNoVowels is the answer
        return phraseWithNoVowels.toString();
    }

    /**
     * This method takes two parameters. A string phrase and a char ch.
     * and returns a String with all the char ch replaced by either '*' or '+'
     * depending on the index location of the char ch(odd or even).
     *
     * @param phrase String to be returned with the all the char ch replaced
     * @param ch     is the character to be replaced.
     * @return a String that is the String phrase with all the character ch replaced
     */
    private String emphasize(String phrase, char ch)
    {
        StringBuilder phraseWithNoCh = new StringBuilder(phrase);
        //lowerCh is used at line 68 as a sentinel variable
        char lowerCh = Character.toLowerCase(ch);
        //count from 0 < length of phrase
        for (int k = 0; k < phrase.length(); k++) {
            char currentChar = phrase.charAt(k);
            //Store currentChar in lower case
            char currentCharlower = Character.toLowerCase(currentChar);
            //for my own understanding purpose
            System.out.println("currentChar accumulated from index k: " + currentChar);
            System.out.println("currentChar in lower case: " + currentCharlower);

            if (k % 2 == 0 && lowerCh == currentCharlower) {
                phraseWithNoCh.setCharAt(k, '*');
            }
            if (k % 2 == 1 && lowerCh == currentCharlower) {
                phraseWithNoCh.setCharAt(k, '+');
            }
        }
        System.out.println("The answer is the String inside the phraseWithCh()StringBuilder: ");
        return phraseWithNoCh.toString();
    }

    /**
     *
     */
    public static void main(String[] args)
    {
        FileResource file = new FileResource();
        String message = file.asString();

        WordPlay word = new WordPlay();

        System.out.println("isVowel() Method: ");
        System.out.println("=================");
        boolean testIsVowel = word.isVowel('B');
        System.out.println("Is 'B' a vowel? " + testIsVowel);
        System.out.println();

        System.out.println("replaceVowels() Method: ");
        System.out.println("=======================");
        String testReplaceVowels = word.replaceVowel("Answer ", '*');
        System.out.println(testReplaceVowels);
        System.out.println();

        System.out.println("emphasis() Method");
        System.out.println("==================");
        String testEmphasis = word.emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(testEmphasis);
        System.out.println();
    }
    //end of the main  method

}
