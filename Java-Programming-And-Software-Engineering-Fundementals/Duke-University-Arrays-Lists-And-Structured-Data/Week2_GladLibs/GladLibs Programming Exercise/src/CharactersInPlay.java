/**
 *  This program called Characther Names, determines the characters in one
 *  of Shakespeare’s plays that have the most speaking parts (the main characters).
 */

import edu.duke.*;
import java.util.*;

public class CharactersInPlay
{
    private ArrayList<String> namesOfTheCharacters;
    private ArrayList<Integer> countsForEachCharacter;

    public CharactersInPlay()
    {
        namesOfTheCharacters = new ArrayList<String>();
        countsForEachCharacter = new ArrayList<Integer>();
    }

    /**
     * This method should update the two ArrayLists, adding the character’s name
     * if it is not already there, and counting this line as one speaking part for
     * this person.
     * @param person a String that is the speaking character's name.
     */
    private void update(String person)
    {

        //If person is not already in names, add person as a new names object and 1 to the arrayList as a new object:

        int indexOfTheCharacterName = namesOfTheCharacters.indexOf(person);

        if (indexOfTheCharacterName == -1)
        {
            namesOfTheCharacters.add(person);
            countsForEachCharacter.add(1);
        }

//          If person is already in names, change counts adding 1 every time we find a person.
//          This is so intelligent. By playing with the index of the Sting arrayList, you can
//          manipulate the counts without modifying the names ArrayList

        else {
            int indexCounts = countsForEachCharacter.get(indexOfTheCharacterName);
            countsForEachCharacter.set(indexOfTheCharacterName, indexCounts + 1);
        }

    }

    /**
     * This method opens a file and reads the file line by the line. For each line,
     * if there is a period on the line, extract the possible name of the speaking
     * part(Everything up to the period), and call update to count it as an occurrence for this person. Make Sure
     * You clean the appropriate the instance variable before each new file.
     */
    private void findAllCharacters()
    {

        FileResource file = new FileResource();
        String firstCharacterNameToOccur = null;

        for(String line : file.lines())
        {
//            if (line.indexOf(".") != -1)
            if (line.contains("."))
            {
                firstCharacterNameToOccur = line.substring(0, line.indexOf("."));
                update(firstCharacterNameToOccur);
            }
        }
    }

    /**
     *
     */
    private void tester()
    {
        countsForEachCharacter.clear();
        namesOfTheCharacters.clear();

        findAllCharacters();

        for (int k = 0; k < namesOfTheCharacters.size(); k++)
        {
            //System.out.println(namesOfTheCharacters.get(k) + "\t" + countsForEachChracter.get(k));
            if (countsForEachCharacter.get(k) > 1)
            {
                System.out.println(namesOfTheCharacters.get(k) + "\t" + countsForEachCharacter.get(k));
           }
        }
        charactersWithNumParts(10,15);
    }

    /**
     * Assuming that num1 is less or equal to num2, this method should print out
     * the names of all those characters that have exactly NUMBER speaking parts,
     * where NUMBER is greater than or equal to num1 and less than or equal to num2.
     * @param num1 int that is less or equal to num2
     * @param num2 int.
     */
    private void charactersWithNumParts(int num1, int num2)
    {
        System.out.println("Characters that have between "+ num1 + " and " + num2+ " lines:");

        for (int k = 0; k < namesOfTheCharacters.size(); k++)
        {
            if (countsForEachCharacter.get(k) >= num1 && countsForEachCharacter.get(k)<= num2)
            {
                System.out.println(namesOfTheCharacters.get(k) + "\t" + countsForEachCharacter.get(k));
            }
        }
    }

    public static void main(String[] args)
    {
        CharactersInPlay essay = new CharactersInPlay();
        essay.tester();
    }
}
