
/**
 * Write a program to determine which words occur in the greatest
 * number of files, and for each word, which files they occur in.
 */
import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles
{
    /**
     * Create a private variable to store a HashMap that maps a word to an ArrayList of
     * filenames.
     */
    private HashMap<String, ArrayList<String>> myMap;
    // Write a constructor to initialize the HashMap variable, Using Default constructor to initialize the HashMap variable
    public WordsInFiles()
    {
        myMap = new HashMap<String, ArrayList<String>>();
    }

    /**
     * This method adds all the words from the File file into the map.
     * If a word is not in the map then u must create a new ArrayList of type String with this word.
     * and have the word map to this ArrayList. If a word is already in the map,
     * then add the current filename to its ArrayList, unless the filename is already in the ArrayList.
     */
    private void addWordsFromFile(File file)
    {
        FileResource myFile = new FileResource(file.toString());
        String allName = file.getName();

        for (String word : myFile.words())
        {
            if (!myMap.containsKey(word))
            {
                myMap.put(word, new ArrayList<String>());
                myMap.get(word).add(allName);
            }
            else if (myMap.containsKey(word))
            {
                if (!myMap.get(word).contains(allName)) myMap.get(word).add(allName);
            }
        }
    }

    /**
     * This method first clears the map, and then uses a DirectoryResource to select a group of files.
     * For each file, it puts all of its words into the map by calling the method addWordsFromFile
     * The remaining methods to write all assume that the HashMap has been built.
     */
    private void buildWordFileMap()
    {
        myMap.clear();
        DirectoryResource myDirectory = new DirectoryResource();
        for (File file : myDirectory.selectedFiles())
        {
            addWordsFromFile(file);
        }
    }

    /**
     * This method returns the maximum number of files any word appears in,
     * considering all words from a group of files
     */
    private int maxNumber()
    {
        int maximumnumber = 0;
        int currentnumber;

        for (String word: myMap.keySet())
        {
            currentnumber = myMap.get(word).size();
            if (maximumnumber < currentnumber)maximumnumber = currentnumber;
        }
        return maximumnumber;
    }

    /**
     *  Write the method wordsInNumFiles that has one integer parameter called number .
     * This method returns an ArrayList of words that appear in exactly number files.
     */
    private ArrayList<String> wordsInNumFiles(int number)
    {
        int currentNumber = 0;

        // Construct an empty list to add words here //

        ArrayList<String> wordList = new ArrayList<String>();

        for (String word: myMap.keySet())
        {
            currentNumber = myMap.get(word).size();
            if (currentNumber==number) wordList.add(word);
        }
        return wordList;
    }

    /**
     * Write the void method printFilesIn that has one String parameter named word .
     * This method prints the names of the files this word appears in, one filename per line.
     */
    private void printFilesIn(String word)
    {

        word = "tree";
        System.out.println( "'" + word + "'" + " appears in the files : \t ");

        ArrayList<String> list = new ArrayList<String>();

        for (String current: myMap.keySet())
        {
            if (current.equals(word))
            {
                list = myMap.get(current);
            }
        }
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i) + " ");
        }
        System.out.println("Word in file from printFilesIn : "+ word);
    }

    /**
     *  Write the void method tester that has no parameters.
     * This method should call buildWordFileMap() to select a group of files and build a HashMap of words, with each word mapped to an ArrayList of the filenames this word appears in.
     * determine the maximum number of files any word is in, considering all words.
     * determine all the words that are in the maximum number of files and for each such word, print the filenames of the files it is in.
     */
    public void tester()
    {
        System.out.println("\n");
        buildWordFileMap();
        int maximumNumber = maxNumber();
        //int maximumNumber = 4;
        ArrayList<String> list = wordsInNumFiles(maximumNumber);

        System.out.println("The greatest number of files a word appears in is " +
                maximumNumber + ", and there are " + list.size() + " such words which are: "  );
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i) + " ");
        }
        System.out.println("");
        for (int j = 0; j < list.size(); j++)
        {
            printFilesIn(list.get(j));
        }
    }

    public static void main(String[] args)
    {
        WordsInFiles word = new WordsInFiles();
        word.tester();
//        word.wordsInNumFiles(4);
//        word.printFilesIn("cats");
    }
}
