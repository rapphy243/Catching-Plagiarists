
/**
 * Write a description of class EssayPair here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EssayPair
{
    private NumDocs size;

    private String path1;
    private String path2;

    private int numCommonPhrases;

    public EssayPair(NumDocs size, String path1, String path2) throws java.io.FileNotFoundException
    {
        this.size = size;
        this.path1 = path1;
        this.path2 = path2;

        this.numCommonPhrases = Parser.getCommonPhrases(getFullPath1(), getFullPath2()).size();
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
        return this.numCommonPhrases;
    }

    public String toString()
    {
        return "[" + path1 + ", " + path2 + "]";
    }
}
