import java.util.Scanner;
import java.util.*;
import java.io.*;

/**
 * Write a description of class Parsing here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Parsing
{
    // Creates a list of all the words in the file
    // Then uses a (slightly bad) implementation of sliding window to parse words
    // https://youtu.be/y2d0VHdvfdc?si=5nX0Ueb2XYe6yx90-
    // https://www.geeksforgeeks.org/window-sliding-technique
    public static Set<String> listParse(String fullPath, int numWords)
    {
        Scanner file;

        // https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
        // tldr use a hashset for no duplicates
        Set<String> set = new HashSet<String>();
        try 
        {
            file = new Scanner(new File(fullPath));
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            System.out.println(fullPath);
            System.out.println("File path is invalid.");
            System.out.println("Set will be empty.");
            return set;
        }

        // Create list full of all the stripped words in the file.
        List<String> wordList = new ArrayList<String>();

        // Iterate through the file and add words to the list and phrases to the set
        while (file.hasNext()) 
        {
            String word = file.next().replaceAll("[^A-z]", "").toLowerCase(); // strip away all punctuation, and set to lowercase
            wordList.add(word); // Add word to list
        }

        // Loop through the list of words and create the rest of the n-contiguous-words
        for (int i = 0; i < numWords; i++) // Our offset
        {
            for (int x = i; x <= wordList.size() - numWords; x += numWords) // Our window
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

    // Uses a queue to create each phrase
    public static Set<String> queueParse(String fullPath, int numWords)
    {
        Scanner file;

        // https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
        // tldr use a hashset for no duplicates
        Set<String> set = new HashSet<String>();
        try 
        {
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
        // Use queue to go through the file once
        Queue<String> queue = new LinkedList<String>();

        while (file.hasNext()) 
        {
            String word = file.next().replaceAll("[^A-z]", "").toLowerCase(); // strip away all punctuation, and set to lowercase

            queue.add(word); // add word to queue
            if (queue.size() == numWords) // if we have numWords size in queue
            {
                String phrase = "";

                for (String w : queue) // get each word in queue and make phrase
                {
                    phrase += w;
                }
                set.add(phrase); // add phrase to queue
                queue.remove(); // remove oldest word
            }
        }           
        return set;
    }

    public static Set<String> nLoopParse(String fullPath, int numWords)
    {
        Scanner file;

        // https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
        // tldr use a hashset for no duplicates
        Set<String> set = new HashSet<String>();

        for (int i = 0; i < numWords; i++)
        {
            try 
            {
                file = new Scanner(new File(fullPath));
            }
            catch (java.io.FileNotFoundException fnfe)
            {
                System.out.println(fullPath);
                System.out.println("File path is invalid.");
                System.out.println("Set will be empty.");
                return set;
            }
            for (int x = 0; x < i; x++) // Make our offset for each pass
            {
                file.next();
            }
            nLoopParse(file, set, numWords);
        }
        return set;
    }

    private static void nLoopParse(Scanner file, Set set, int numWords)
    {
        while(file.hasNext())
        {
            String phrase = "";
            // read 'numWords' words from file
            for(int j = 0; j < numWords; j++)
            {
                if(file.hasNext())
                // strip away all punctuation, and set to lowercase
                    phrase += file.next().replaceAll("[^A-z]","").toLowerCase();
                else
                    phrase = null; // not enough words at end of file
            }
            if (phrase != null)
            {
                set.add(phrase);
            }
        }
    }

    public static void main(String[] args)
    {
        // Test to make sure each method returns the same size
        String path = "./Small number of documents/erk185.shtml.txt";
        System.out.println("Provided code for parsing size: " + nLoopParse(path,4).size());
        System.out.println("Window code for parsing size: " + listParse(path,4).size());
        System.out.println("Queue code for parsing size: " + queueParse(path,4).size());
    }
}
