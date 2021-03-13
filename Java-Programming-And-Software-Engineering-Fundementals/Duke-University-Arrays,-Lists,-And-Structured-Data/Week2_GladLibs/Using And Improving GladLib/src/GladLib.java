/**
 *
 */

import edu.duke.FileResource;
import edu.duke.URLResource;
import java.util.ArrayList;
import java.util.Random;

public class GladLib
{
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> trackList;
    private Random myRandom;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    /**
     *
     */
    public GladLib()
    {
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLib(String source)
    {
        initializeFromSource(source);
        myRandom = new Random();
        trackList = new ArrayList<String>();//Keeps tack of seen words
    }

    /**
     *
     */
    private void initializeFromSource(String source)
    {
        adjectiveList= readIt(source+"/adjective.txt");
        nounList = readIt(source + "/noun.txt");
        colorList = readIt(source + "/color.txt");
        countryList = readIt(source + "/country.txt");
        nameList = readIt(source + "/name.txt");
        animalList = readIt(source + "/animal.txt");
        timeList = readIt(source + "/timeframe.txt");
        verbList = readIt(source + "/verb.txt");
        fruitList = readIt(source + "/fruit.txt");
    }

    /**
     * return an index at random
     */
    private String randomFrom(ArrayList<String> source)
    {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    /**
     *
     */
    private String getSubstitute(String label)
    {
        if (label.equals("country"))
        {
            return randomFrom(countryList);
        }
        if (label.equals("color"))
        {
            return randomFrom(colorList);
        }
        if (label.equals("noun"))
        {
            return randomFrom(nounList);
        }
        if (label.equals("name"))
        {
            return randomFrom(nameList);
        }
        if (label.equals("adjective"))
        {
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal"))
        {
            return randomFrom(animalList);
        }
        if (label.equals("timeframe"))
        {
            return randomFrom(timeList);
        }
        if (label.equals("verb"))
        {
            return randomFrom(verbList);
        }
        if (label.equals("fruit"))
        {
            return randomFrom(fruitList);
        }
        if (label.equals("number"))
        {
            return ""+ myRandom.nextInt(50) + 5;
        }
            return "**UNKNOWN**";
        }

        /**
         *
         */
        private String processWord(String word)
        {
            int firstAngleBracket = word.indexOf("<");
            int lastAngleBracket = word.indexOf(">",firstAngleBracket);

            if (firstAngleBracket == -1 || lastAngleBracket == -1)
            {
                return word;
            }

            String prefix = word.substring(0, firstAngleBracket);
            String suffix = word.substring(lastAngleBracket + 1);
            String sub = getSubstitute(word.substring(firstAngleBracket + 1, lastAngleBracket));

            while (trackList.contains(sub))
            {
                sub = getSubstitute(word.substring(firstAngleBracket + 1, lastAngleBracket));
            }
            trackList.add(sub);
            return prefix + sub + suffix;
        }

        private void printOut(String s, int lineWidth)
        {
            int charsWritten = 0;

            for(String w : s.split("\\s+"))
            {
                if (charsWritten + w.length() > lineWidth)
                {
                    System.out.println();
                    charsWritten = 0;
                }
                System.out.print(w + " ");
                charsWritten += w.length() + 1;
            }
        }

        private String fromTemplate(String source)
        {
            String story = "";
            if (source.startsWith("http")) {
                URLResource myFile = new URLResource(source);
                for(String word : myFile.words())
                {
                    story = story + processWord(word) + " ";
                }
            }
            else {
                FileResource myFile = new FileResource(source);
                for(String word : myFile.words())
                {
                    story = story + processWord(word) + " ";
                }
            }
            return story;
        }

    private ArrayList<String> readIt(String source)
    {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http"))
        {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines())
            {
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines())
            {
                list.add(line);
            }
        }
        return list;
    }

    private void makeStory()
    {
        trackList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        //total number of replaced words
        System.out.println("\nReplaced Words: " + trackList.size());
    }

    public static void main(String[] args)
    {
        GladLib tellStory = new GladLib(dataSourceDirectory);
        tellStory.makeStory();
    }
}
