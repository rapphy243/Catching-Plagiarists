import java.io.File;

/**
 * Write a description of class EssayPair here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EssayPair implements Comparable<EssayPair>
{
    private File folder;

    private Essay essay1;
    private Essay essay2;
    
    private int commonPhrases;

    public EssayPair(File folder, Essay essay1, Essay essay2)   
    {
        this.folder = folder;
        this.essay1 = essay1;
        this.essay2 = essay2;
        
        this.commonPhrases = Parser.getCommonPhrases(essay1, essay2).size();
    }

    public Essay getEssay1()
    {
        return this.essay1;
    }

    public Essay getEssay2()
    {
        return this.essay2;
    }

    public String getFullPath1()
    {
        return essay1.toString();
    }

    public String getFullPath2()
    {
        return essay2.toString();
    }

    public int getNumCommonPhrases()
    {
        return this.commonPhrases;
    }

    public String toString()
    {
        return "[" + essay1.getName() + ", " + essay2.getName() + "]";
    }
    
    public int compareTo(EssayPair other)
    {
        return this.commonPhrases - other.getNumCommonPhrases();
    }
}
