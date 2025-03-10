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
    public static Set<String> parseFile(String path, int numWords)
    {
        Scanner file;

        try {
            file = new Scanner(new File(path));
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            return new HashSet<String>();
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

    public static Set<String> getCommonPhrases(Essay essay1, Essay essay2)
    {
        Set<String> set = essay1.getParse();
        set.retainAll(essay2.getParse());
        return set;
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

        EssayPair example = new EssayPair(file, new Essay(file, "jrf1109.shtml.txt", 4), new Essay(file,"sra31.shtml.txt", 4));
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());

        example = new EssayPair(file, new Essay(file, "abf0704.shtml.txt", 4), new Essay(file, "edo26.shtml.txt", 4));
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());

        example = new EssayPair(file, new Essay(file, "abf0704.shtml.txt", 4), new Essay(file, "abf70402.shtml.txt", 4));
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());

        example = new EssayPair(file, new Essay(file, "abf70402.shtml.txt", 4), new Essay(file, "edo26.shtml.txt", 4));
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());

        example = new EssayPair(file, new Essay(file, "abf0704.shtml.txt", 4), new Essay(file, "edo20.shtml.txt", 4));
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getNumCommonPhrases());
    }
}
