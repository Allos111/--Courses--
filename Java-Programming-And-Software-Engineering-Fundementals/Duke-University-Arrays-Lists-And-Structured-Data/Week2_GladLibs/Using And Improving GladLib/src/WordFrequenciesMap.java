/**
 * WORD FREQUENCY COUNTER
 * ----------------------
 * Find out and keep track of how many times each word occurs in a file
 */
import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.HashMap;

public class WordFrequenciesMap
{
    private FileResource myFile;

    public WordFrequenciesMap(String filename)
    {
        myFile = new FileResource(filename);
    }

    /**
     * This method loops over a file and counts how many words/Unique words
     * are in the file using the ArrayList data Structure.
     */
    public void countWords()
    {
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Integer> counters = new ArrayList<Integer>();
        int total = 0;
        for(String wordInFile : myFile.words())
        {
            wordInFile = wordInFile.toLowerCase();
            int index = words.indexOf(wordInFile);
            if (index == -1)
            {
                words.add(wordInFile);
                counters.add(1);
            }
            else {
                int value = counters.get(index);
                counters.set(index, value + 1);
            }
        }

        for(int k=0; k < words.size(); k++)
        {
            if (counters.get(k) > 500)
            {
                System.out.println(counters.get(k)+"\t"+words.get(k));
            }
            total += counters.get(k);
        }
        System.out.println("total words found: " + total + " total unique words found = "+words.size());
    }

    /**
     * This method loops over a file and counts how many words/Unique words
     * are in the file using the HashMap data Structure.
     */
    public void countWordsMap()
    {
        HashMap<String, Integer> myMap = new HashMap<String,Integer>();
        int total = 0;

        for(String wordInFile : myFile.words())
        {
            wordInFile = wordInFile.toLowerCase();
            if (!myMap.containsKey(wordInFile))
            {
                myMap.put(wordInFile, 1);
            }
            else {
                myMap.put(wordInFile, myMap.get(wordInFile) + 1);
            }
        }

        for(String wordInFile : myMap.keySet())
        {
            int value = myMap.get(wordInFile);
            if (value > 500)
            {
                System.out.println(value + "\t" + wordInFile);
            }
            total += value;
        }
        System.out.println("total words found: " + total +
                " total unique words found = "+ myMap.keySet().size());
    }

    public void tester()
    {
        System.out.println("\n");
        System.out.println("Words count using ArrayLIst");
        System.out.println("============================");
        double start = System.currentTimeMillis();
        countWords();
        double end = System.currentTimeMillis();
        double time = (end-start)/1000;
        System.out.println("time = " + time);
        System.out.println("\n");
        System.out.println("Words count using HashMap: ");
        System.out.println("===========================");
        start = System.currentTimeMillis();
        countWordsMap();
        end = System.currentTimeMillis();
        time = (end-start)/1000;
        System.out.println("time = " + time);
    }

    public static void main (String[] args)
    {
        String filename = "data/errors.txt";
        WordFrequenciesMap wordCounter = new WordFrequenciesMap(filename);
        wordCounter.tester();

    }

}
