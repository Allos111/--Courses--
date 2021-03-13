package assignments.week1;

public class PhraseFilter implements Filter
{
    private String where;
    private String phrase;
    private String name = "PhraseFilter";

    public PhraseFilter (String whereToSearch, String phraseToSearch)
    {
        where = whereToSearch;
        phrase = phraseToSearch;
    }

    public boolean satisfies (QuakeEntry qe)
    {
        if (where.equals("start"))
        {
            return qe.getInfo().startsWith(phrase);
        }
        else if (where.equals("any"))
        {
            return qe.getInfo().contains(phrase);
        }
        else if (where.equals("end"))
        {
            return qe.getInfo().endsWith(phrase);
        }
        return false;
    }

    public String getName ()
    {
        return name;
    }
}
