/**
 * This program counts the total number of a word in a file
 * The same concept maybe used to find out how many IP addresses visit a web page
 * We'll iterate over either a FileResource or URLResource. This program contains two methods that show
 *  * how Array and Arrayalist can be used to solve the same problem.
 */

import java.util.*;
import edu.duke.*;

public class CountWords
{
    private StorageResource myWords;
    private Random rand;
    private FileResource resource;

    /**
     * The constructor
     */
    public CountWords()
    {
        //Initialization of the instance variable
        myWords = new StorageResource();
        rand = new Random();
        resource = new FileResource();
    }

    /**
     * This method returns the total number of words read using the .size method.
     */
    private int getCount()
    {
        return myWords.size();
    }

    /**
     * This method returns an element chosen at random using the iterable interface
     * that StorageResource provides.
     * @return a String that is a word chosen randomly from the
     * StorageResource myWords.
     */
    private String getRandom()
    {
        int choice = rand.nextInt(myWords.size());
        for (String s: myWords.data())
        {
            if (choice == 0)
            {
                return s;
            }
            choice = choice - 1;
        }
        return "*** NEVER HAPPENS ***";
    }

    /**
     * This method reads through a file or URL and add each word read to myWords.
     */
    private void readWords(String source)
    {
        myWords.clear();

        if (source.startsWith("http") || (source.startsWith("https")))
        {
            URLResource resource = new URLResource(source);
            for (String word : resource.words())
            {
                myWords.add(word.toLowerCase());
            }
        }
        else
        {
            for (String word : resource.words())
            {
                myWords.add(word.toLowerCase());
            }
        }
    }

    /**
     * This method returns a boolean value. This value is used to ensure that
     * the .add method is called only when the Storage research object myWords
     * doesn't contain a word.
     * @param list
     * @param size
     * @param word
     * @return
     */
    private boolean contains(String[] list, int size, String word)
    {
        for (int k = 0; k < size; k++)
        {
            if (list[k].equals(word))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns the number of unique words in a file using an Array
     * to do the count. It calls getCount to figure out the size of our StorageResourse
     * object and it calls contains to ensure of uniqueness of the word before
     * adding it and it keeps track of the unique words found.
     * @return an int which the number of unique words in the file.
     */
    private int countDifferentArray()
    {
        int diffCount = 0;
        String[] words = new String[getCount()];
        for (String s : myWords.data())
        {
            if (! contains(words, diffCount, s))
            {
                words[diffCount] = s;
                diffCount++;
            }
        }
        return diffCount;
    }

    /**
     * This method returns the number of unique words in a file using an ArrayList
     * to do the count. It calls getCount to figure out the size of our StorageResourse
     * object and it calls contains to ensure of uniqueness of the word before
     * adding it and it keeps track of the unique words found.
     * @return an int which the number of unique words in the file.
     */
    private int countDifferentArrayList()
    {
        ArrayList<String> words = new ArrayList<String>();
        for (String s : myWords.data())
        {
            if (!words.contains(s))
            {
                words.add(s);
            }
        }
        return words.size();
    }

    private void tester()
    {
        String read = resource.asString();
        readWords(read);
        //readWords("http://dukelearntoprogram.com/data/confucius.txt");
        System.out.println("number of words read: "+ getCount());
        int countArray = countDifferentArray();
        int countArraylist = countDifferentArrayList();
        System.out.println(countArray +"\t"+ countArraylist);
    }

    private String getRandomWord(String[] words)
    {
        int index = rand.nextInt(words.length);
        return words[index];
    }

    public void randomTester()
    {
        readWords("data/confucius.txt");
        System.out.println("starting");
        int RAND_SIZE = 100000;
        for(int k=0; k < RAND_SIZE; k++){
            String word = getRandom();
            if (word.indexOf("*** NEVER") != -1)
            {
                System.out.println(word);
            }
        }
        System.out.println("done with randoms");

        String[] words = new String[myWords.size()];
        int index = 0;
        for(String s : myWords.data())
        {
            words[index] = s;
            index += 1;
        }
        System.out.println("starting array");

        for(int k = 0; k < RAND_SIZE; k++)
        {
            String word = getRandomWord(words);
        }
        System.out.println("done with randoms");
    }

    public static void main(String[] args)
    {
        CountWords count = new CountWords();
        count.tester();
    }
}
