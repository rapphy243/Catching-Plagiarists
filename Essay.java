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
        this.parsedFile = this.parseFile(getFullPath(), nCount);
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
    
    private Set<String> parseFile(String fullPath, int numWords)
    {
        Scanner file;
        
        // https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
        // tldr use a hashset for no duplicates
        Set<String> set = new HashSet<>();
        try {
            file = new Scanner(new File(fullPath));
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            System.out.println(fullPath);
            System.out.println("File path is invalid.");
            System.out.println("Set will be empty.");
            return set;
        }
        // Faster implementation of parsing file
        // Create list full of all the stripped words in the file.
        List<String> wordList = new ArrayList<>();

        // Initialize phrase and count variables to make the first pass of n-contiguous-words
        int count = 0;
        String phrase = "";
        
        // Iterate through the file and add words to the list and phrases to the set
        // While doing this create the first pass of n-contiguous-words for the file
        while (file.hasNext()) 
        {
            String word = file.next().replaceAll("[^A-z]", "").toLowerCase(); // Remove all non-alphabetic characters
            wordList.add(word); // Add word to list
            if (count < numWords) // If count is less than numWords, add word to phrase
            {
                phrase += word;
                count++;
            }
            else // If count is equal to numWords, add phrase to set and reset phrase and count
            {
                set.add(phrase);
                phrase = "";
                count = 0;
            }
        }
        
        // Loop through the list of words and create the rest of the n-contiguous-words
        // Using sliding window technique https://www.geeksforgeeks.org/window-sliding-technique/
        for (int i = 1; i < numWords; i++) // Our offset
        {
            for (int x = i; x <= wordList.size() - numWords; x += numWords) // Our window
            {
                phrase = "";

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
        // Test to check word phrases from step 0
        File folder = new File("./Small number of documents");
        Essay essay = new Essay(folder, "erk185.shtml.txt", 4);
        System.out.println("Example word phrases from: " + essay.getFileName());
        System.out.println("Path to file: " + essay.getFullPath());
        System.out.println(essay.getParse());
        System.out.println("There are " + essay.getParse().size() + " 4-word phrases.");
    }
}
