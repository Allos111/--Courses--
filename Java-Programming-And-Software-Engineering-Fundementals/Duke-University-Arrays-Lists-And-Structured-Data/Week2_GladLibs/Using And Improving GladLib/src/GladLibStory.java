/**
 *
 */
import edu.duke.FileResource;
import edu.duke.URLResource;
import java.util.ArrayList;
import java.util.Random;

public class GladLibStory
{
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private Random myRandom;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibStory()
    {
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibStory(String source)
    {
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source)
    {
        adjectiveList= readIt(source +"/adjective.txt");
        nounList = readIt(source +"/noun.txt");
        colorList = readIt(source +"/color.txt");
        countryList = readIt(source +"/country.txt");
        nameList = readIt(source +"/name.txt");
        animalList = readIt(source +"/animal.txt");
        timeList = readIt(source +"/timeframe.txt");
    }

    private String randomFrom(ArrayList<String> source)
    {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label)
    {
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("number")){
            return ""+ myRandom.nextInt(50) + 5;
        }
        return "**UNKNOWN**";
    }
    /**
     *
     */
    private String processWord(String word)
    {
        int first = word.indexOf("<");
        int last = word.indexOf(">", first);

        if (first == -1 || last == -1)
        {
            return word;
        }
        String prefix = word.substring(0, first);
        String suffix = word.substring(last + 1);
        String sub = getSubstitute (word.substring(first + 1, last));
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
        StringBuilder story = new StringBuilder(); //why not initialise to null
        if (source.startsWith("http"))
        {
            URLResource resource = new URLResource(source);
            for(String word : resource.words())
            {
                story.append(processWord(word)).append(" ");
            }
        }
        else
            {
            FileResource resource = new FileResource(source);
            for(String word : resource.words())
            {
                story.append(processWord(word)).append(" ");
            }
        }
        return story.toString();
    }

    private ArrayList<String> readIt(String source)
    {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http"))
        {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines())
            {
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines())
            {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory()
    {
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
    }

    public static void main (String[] args)
    {
        GladLibStory storyTeller = new GladLibStory(dataSourceDirectory);
        storyTeller.makeStory();
    }
}
