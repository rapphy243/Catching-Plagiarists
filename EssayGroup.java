import java.util.*;
import java.io.File;

/**
 * This EssayGroup class represents . . .
 *
 * @author  (your name)
 * @version (todays date)
 */
public class EssayGroup
{
    // Used in the GUI.
    // Holds a ArrayList of EssayPairs
    // Then uses Collections to sort them
    // in decending order by how many hits 
    // each pair has.
    
    List<EssayPair> list;
    
    public EssayGroup(File folder, List<String> listOfFiles, int numWords) // List of file paths
    {
        System.out.println("Creating file pairs...");
        this.list = new ArrayList<EssayPair>();
        
        List<Essay> tempList = new ArrayList<Essay>();
        int size = listOfFiles.size();
        if (size > 200)
        {
            System.out.println("This may take a while.");            
        }
        for (int i = 0; i < size; i++)
        {
            tempList.add(new Essay(folder, listOfFiles.get(i), numWords));
        }
    
        for (int i = 0; i < size; i++)
        {
            Essay tempEssay = tempList.get(i);
            progress(i, size);
            for (int x = i + 1; x < size; x++)
            {
                EssayPair pair = new EssayPair(tempEssay, tempList.get(x));
                if (pair.getCommonPhraseHits() >= 0)
                {
                    this.list.add(pair);
                }
            }
        }
        
        if (size > 200)
        {
            System.out.println("=================");
            System.out.println("Sorting Essay Pairs:");
            System.out.println("This may take a while.");
        }
        Collections.sort(this.list, Collections.reverseOrder());
    }
    
    public EssayGroup(File folder, List<String> listOfFiles, int numWords, int threshold)
    {
        System.out.println("Creating file pairs...");
        this.list = new ArrayList<EssayPair>();
        
        List<Essay> tempList = new ArrayList<Essay>();
        int size = listOfFiles.size();
        if (size > 200)
        {
            System.out.println("This may take a while.");            
        }
        for (int i = 0; i < size; i++)
        {
            tempList.add(new Essay(folder, listOfFiles.get(i), numWords));
        }
    
        for (int i = 0; i < size; i++)
        {
            Essay tempEssay = tempList.get(i);
            progress(i, size);
            for (int x = i + 1; x < size; x++)
            {
                EssayPair pair = new EssayPair(tempEssay, tempList.get(x));
                if (pair.getCommonPhraseHits() >= threshold)
                {
                    this.list.add(pair);
                }
            }
        }
        
        if (size > 200 && threshold < 5)
        {
            System.out.println("=================");
            System.out.println("Sorting Essay Pairs:");
            System.out.println("This may take a while.");
        }
        Collections.sort(this.list, Collections.reverseOrder());
    }
    
    public void print()
    {
        System.out.println("=================");
        System.out.println("Printing essay pairs with hits:");
        for (int i = 0; i < list.size(); i++)
        {
            System.out.print(list.get(i).toString() + " -> ");
            System.out.println(list.get(i).getCommonPhraseHits());
        }
    }
    
    public void print(int topResults)
    {
        if (topResults > list.size())
        {
            print();
            return;
        }
        System.out.println("=================");
        System.out.println("Printing first " + topResults + " essay pairs with hits:");
        for (int i = 0; i < topResults; i++)
        {
            System.out.print(list.get(i).toString() + " -> ");
            System.out.println(list.get(i).getCommonPhraseHits());
        }
    }
    
    public static void progress(int curr, int max) 
    {
        int percent = (int) Math.round(100.0 * curr/ max);
        int num = percent / 2;

        String bar = "[";
        for (int i = 0; i < num; i++) 
        {
            bar += "=";
        }

        while (bar.length() < 50) 
        {
            bar += " ";
        }

        bar += "] " + percent + "%";
        System.out.println(bar);
    }
    
    public static void main(String[] args)
    {
        File folder = new File("./Small number of documents");
        
        long start = System.currentTimeMillis();
        EssayGroup example = new EssayGroup(folder, Directories.getFileNames(folder), 4);
        example.print();
        double timeToCreate = (double) (System.currentTimeMillis() - start) / 1000.0;
        System.out.println(String.format("Creation time took: %4.2f seconds", timeToCreate));
        
        folder = new File("./Medium number of documents");
        start = System.currentTimeMillis();
        example = new EssayGroup(folder, Directories.getFileNames(folder), 4);
        timeToCreate = (double) (System.currentTimeMillis() - start) / 1000.0;
        example.print();
        System.out.println(String.format("Creation time took: %4.2f seconds", timeToCreate));

        folder = new File("./Large number of documents");
        start = System.currentTimeMillis();
        example = new EssayGroup(folder, Directories.getFileNames(folder), 4);
        timeToCreate = (double) (System.currentTimeMillis() - start) / 1000.0;
        example.print(20);
        System.out.println(String.format("Creation time took: %4.2f seconds", timeToCreate));
    }
}
