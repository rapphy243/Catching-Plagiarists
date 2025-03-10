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


    public static Set<String> getCommonPhrases(String path1, String path2, int phrases) throws FileNotFoundException
    {
        Set<String> set1 = parseFile(path1, phrases);
        Set<String> set2 = parseFile(path2, phrases);
        set1.retainAll(set2);
        return set1;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File("./Small number of documents");
        Set<String> list = parseFile(file + "/erk185.shtml.txt", 4);
        System.out.println("Example word phrases from: " + file);
        System.out.println(parseFile(file + "/erk185.shtml.txt", 4));
        System.out.println("There are " + list.size() + " 4-word phrases.");
        System.out.println("=================");
        System.out.println("Testing Hardcoded Examples:");
        EssayPair example = new EssayPair(file, "jrf1109.shtml.txt", "sra31.shtml.txt", 4);
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());

        example = new EssayPair(file, "abf0704.shtml.txt", "edo26.shtml.txt", 4);
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());

        example = new EssayPair(file, "abf0704.shtml.txt", "abf70402.shtml.txt", 4);
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());

        example = new EssayPair(file, "abf70402.shtml.txt", "edo26.shtml.txt", 4);
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());

        example = new EssayPair(file, "abf0704.shtml.txt", "edo20.shtml.txt", 4);
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());
    }
}
