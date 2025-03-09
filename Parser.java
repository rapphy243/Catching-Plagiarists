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
    public static Set<String> parseFile(String path, int numWords) throws FileNotFoundException
    {
        Scanner file = new Scanner(new File(path));
        // https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
        // tldr use a hashset for no duplicates
        Set<String> set = new HashSet<String>();
        
        for (int i = 0; i < numWords; i++)
        {
            parseFile(set, file, numWords, i);
            file = new Scanner(new File(path));            
        }
        
        return set;
    }
    
    public static void parseFile(Set<String> set, Scanner file, int numWords, int skip) throws FileNotFoundException
    {
        for (int i = 0; i < skip; i++)
        {
            file.next();
        }
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
                set.add(phrase);
            }
        }
    }
    
    public static List<String> compareSets(Set set1, Set set2)
    {
        List<String> temp = new ArrayList<String>();
        Iterator<String> it = set2.iterator();
        
        while (it.hasNext())
        {
            String tempStr = it.next();
            if (set1.contains(tempStr))
            {
                temp.add(tempStr);
            }
        }
        return temp;
    }
    
    public static List<String> getCommonPhrases(String path1, String path2) throws FileNotFoundException
    {
        Set<String> set1 = parseFile(path1, 4);
        
        Set<String> set2 = parseFile(path2, 4);
        
        return compareSets(set1,set2);
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        String path = Directories.directoryMap.get(NumDocs.SMALL);
        Set<String> list = parseFile(path + "/erk185.shtml.txt", 4);
        System.out.println("Example word phrases from: " + path);
        System.out.println(parseFile(path + "/erk185.shtml.txt", 4));
        System.out.println("There are " + list.size() + " 4-word phrases.");
        System.out.println("=================");
        System.out.println("Testing Hardcoded Examples:");
        EssayPair example = new EssayPair(NumDocs.SMALL, "jrf1109.shtml.txt", "sra31.shtml.txt");
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());

        example = new EssayPair(NumDocs.SMALL, "abf0704.shtml.txt", "edo26.shtml.txt");
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());

        example = new EssayPair(NumDocs.SMALL, "abf0704.shtml.txt", "abf70402.shtml.txt");
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());

        example = new EssayPair(NumDocs.SMALL, "abf70402.shtml.txt", "edo26.shtml.txt");
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());

        example = new EssayPair(NumDocs.SMALL, "abf0704.shtml.txt", "edo20.shtml.txt");
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());
    }
}
