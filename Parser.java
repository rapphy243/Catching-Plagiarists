import java.util.*;
import java.io.*;

/**
 * This Parser class represents . . .
 *
 * @author  (your name)
 * @version (todays date)
 */
public class Parser
{
    public static List<String> parseFile(String path, int numWords) throws FileNotFoundException
    {
        Scanner file = new Scanner(new File(path));
        ArrayList<String> list = new ArrayList<String>();
        while(file.hasNext())
        {
            String phrase = "";
            // read 'numWords' words from file
            for(int j = 0; j < numWords; j++)
            {
                if(file.hasNext())
                {
                    // strip away all punctuation, and set to lowercase
                    phrase += file.next().replaceAll("[^A-z]","").toLowerCase();
                }
                else
                {
                    phrase = null; // not enough words at end of file
                }
            }
            if (phrase != null)
            {
                list.add(phrase);
            }
        }
        return list;
    }
    
    public static List<String> getCommonPhrases(File file1, File File2)
    {
        
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        String path = Directories.directoryMap.get(NumDocs.SMALL) + "/erk185.shtml.txt";
        List<String> list = parseFile(path, 4);
        System.out.println("Example word phrases from: " + path);
        System.out.println(parseFile(path, 4));
        System.out.println("There are " + list.size() + " 4-word phrases.");
    }
}
