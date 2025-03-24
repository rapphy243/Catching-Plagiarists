import java.io.File;
import java.util.*;

/**
 * This Essay class represents . . .
 *
 * @author  (your name)
 * @version (todays date)
 */
public class Essay
{
    // Contains folder and fileName of an Essay
    // holds HashSet of the n-contiguous-word 
    // sequences for the essay.
    private File folder;
    private String fileName;

    private int nCount;
    private Set<String> parsedFile;

    public Essay(File folder, String fileName, int nCount)
    {
        this.folder = folder;
        this.fileName = fileName;

        this.nCount = nCount;
        this.parsedFile = Parsing.listParse(getFullPath(), nCount);
    }

    public String getFullPath()
    {
        return this.folder.toString() + "/" + this.fileName;
    }

    public File getFolder()
    {
        return this.folder;
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public int getN()
    {
        return this.nCount;
    }

    public Set<String> getParse()
    {
        return this.parsedFile;
    }

    public static void main(String[] args)
    {
        // Test to check word phrases from step 0
        File folder = new File("./Small number of documents");
        Essay essay = new Essay(folder, "erk185.shtml.txt", 4);
        System.out.println("Example word phrases from: " + essay.getFileName());
        System.out.println("Path to file: " + essay.getFullPath());
        System.out.println(essay.getParse());
        System.out.println("There are " + essay.getParse().size() + " 4-word phrases.");
    }
}
