
/**
 * Write a description of class EssayPair here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EssayPair implements Comparable<EssayPair>
{
    private NumDocs size;

    private String path1;
    private String path2;
    
    private int commonPhrases;

    public EssayPair(NumDocs size, String path1, String path2)    {
        this.size = size;
        this.path1 = path1;
        this.path2 = path2;
        try
        {
            this.commonPhrases = Parser.getCommonPhrases(getFullPath1(), getFullPath2()).size();
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            this.commonPhrases = -1;
        }
    }

    public String getPath1()
    {
        return this.path1;
    }

    public String getPath2()
    {
        return this.path2;
    }

    public String getFullPath1()
    {
        return Directories.directoryMap.get(this.size) + "/" + this.path1;
    }

    public String getFullPath2()
    {
        return Directories.directoryMap.get(this.size) + "/" + this.path2;
    }

    public int getNumCommonPhrases()
    {
        return this.commonPhrases;
    }

    public String toString()
    {
        return "[" + path1 + ", " + path2 + "]";
    }
    
    public int compareTo(EssayPair other)
    {
        return this.commonPhrases - other.getNumCommonPhrases();
    }
}
