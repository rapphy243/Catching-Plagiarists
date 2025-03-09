import java.util.*;
import java.util.stream.Collectors;
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
        // https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
        //tldr use a hashset for no duplicates
        ArrayList<String> list = new ArrayList<String>();
        parseFile(file, list, numWords, 0);
        
        file = new Scanner(new File(path));
        parseFile(file, list, numWords, 1);
        
        file = new Scanner(new File(path));
        parseFile(file, list, numWords, 2);
        
        file = new Scanner(new File(path));
        parseFile(file, list, numWords, 3);
        
        return list.stream().distinct().collect(Collectors.toList()); //TEMP: removes duplicates;
    }
    
    public static void parseFile(Scanner file, List<String> list, int numWords, int skip) throws FileNotFoundException
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
                list.add(phrase);
            }
        }
    }
    
    public static List<String> getCommonPhrases(String path1, String path2) throws FileNotFoundException
    {
        List<String> list1 = parseFile(path1, 4);
        List<String> list2 = parseFile(path2, 4);
        // https://stackoverflow.com/questions/5943330/common-elements-in-two-lists
        list1.retainAll(list2);
        return list1;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        String path = Directories.directoryMap.get(NumDocs.SMALL);
        List<String> list = parseFile(path + "/erk185.shtml.txt", 4);
        System.out.println("Example word phrases from: " + path);
        System.out.println(parseFile(path + "/erk185.shtml.txt", 4));
        System.out.println("There are " + list.size() + " 4-word phrases.");
        System.out.println("=================");
        System.out.println("Testing Examples:");
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
