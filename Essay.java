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
    private File folder;
    private String fileName;
    
    private int nCount;
    private Set<String> parsedFile;
    
    public Essay(File folder, String fileName, int nCount)
    {
        this.folder = folder;
        this.fileName = fileName;
        
        this.nCount = nCount;
        this.parsedFile = parseFile(getFullPath(), this.nCount);
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
    
    private static Set<String> parseFile(String fullPath, int numWords)
    {
        Scanner file;

        try {
            file = new Scanner(new File(fullPath));
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            System.out.println(fullPath);
            System.out.println("File path is invalid.");
            System.out.println("Set will not be instantiated.");
            return null;
        }
        List<String> wordList = new ArrayList<>();

        while (file.hasNext()) 
        {
            wordList.add(file.next().replaceAll("[^A-z]", "").toLowerCase());
        }

        // https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
        // tldr use a hashset for no duplicates
        Set<String> set = new HashSet<>();

        for (int i = 0; i < numWords; i++) 
        {
            for (int x = i; x <= wordList.size() - numWords; x += numWords) 
            {
                String phrase = "";

                for (int j = 0; j < numWords; j++) 
                {
                    phrase += wordList.get(x + j);
                }

                set.add(phrase);
            }
        }

        return set;
    }
    
    public static void main(String[] args)
    {
        File folder = new File("./Small number of documents");
        Essay essay = new Essay(folder, "erk185.shtml.txt", 4);
        System.out.println("Example word phrases from: " + essay.getFileName());
        System.out.println("Path to file: " + essay.getFullPath());
        System.out.println(essay.getParse());
        System.out.println("There are " + essay.getParse().size() + " 4-word phrases.");
    }
}
