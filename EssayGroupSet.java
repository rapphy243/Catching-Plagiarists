import java.util.*;
import java.io.File;

/**
 * This EssayGroup class represents . . .
 *
 * @author  (your name)
 * @version (todays date)
 */
public class EssayGroupSet
{
    TreeSet<EssayPair> set;
    public EssayGroupSet(File folder, List<String> listOfFiles, int numWords) // List of file paths
    {
        this.set = new TreeSet<EssayPair>();
        
        List<Essay> tempList = new ArrayList<Essay>();
        int size = listOfFiles.size();
        
        for (int i = 0; i < size; i++)
        {
            tempList.add(new Essay(folder, listOfFiles.get(i), numWords));
        }

        for (int i = 0; i < size; i++)
        {
            Essay tempEssay = tempList.get(i);
            if (i % (size / 10) == 0)
            {
                progress(i, size);
            }
            for (int x = i + 1; x < size; x++)
            {
                EssayPair pair = new EssayPair(tempEssay, tempList.get(x));
                if (pair.getCommonPhraseHits() >= 0)
                {
                    this.set.add(new EssayPair(tempEssay, tempList.get(x)));
                }
            }
        }
    }

    public EssayGroupSet(File folder, List<String> listOfFiles, int numWords, int threshold)
    {
        this.set = new TreeSet<EssayPair>();

        List<Essay> tempList = new ArrayList<Essay>();
        int size = tempList.size();
        
        for (int i = 0; i < size; i++)
        {
            tempList.add(new Essay(folder, listOfFiles.get(i), numWords));
        }

        for (int i = 0; i < size; i++)
        {
            Essay tempEssay = tempList.get(i);
            if (i % (size / 10) == 0)
            {
                progress(i, size);
            }
            for (int x = i + 1; x < size; x++)
            {
                EssayPair pair = new EssayPair(tempEssay, tempList.get(x));
                if (pair.getCommonPhraseHits() >= threshold)
                {
                    this.set.add(new EssayPair(tempEssay, tempList.get(x)));
                }
            }
        }
        progress(size, size);
        System.out.println("=====================");
        System.out.println("Sorting Colllection");
        if (size > 500)
        {
            System.out.println("This will take a while.");
        }
    }

    public void print()
    {
        System.out.println("=====================");
        System.out.println("Printing Essay Group:");
        Iterator<EssayPair> iterator = set.descendingIterator();
        for (int i = 0; i < set.size(); i++)
        {
            EssayPair pair = iterator.next();
            System.out.print(pair.toString() + " -> ");
            System.out.println(pair.getCommonPhraseHits());
        }
    }

    public void print(int topResults)
    {
        System.out.println("=====================");
        System.out.println("Printing Essay Group:");
        Iterator<EssayPair> iterator = set.descendingIterator();
        for (int i = 0; i < topResults; i++)
        {
            EssayPair pair = iterator.next();
            System.out.print(pair.toString() + " -> ");
            System.out.println(pair.getCommonPhraseHits());
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
        EssayGroupSet example = new EssayGroupSet(folder, Directories.getFileNames(folder), 4);
        example.print();
        double timeToCreate = (double) (System.currentTimeMillis() - start) / 1000.0;
        System.out.println(String.format("Creation time took: %4.2f seconds", timeToCreate));
        
        folder = new File("./Medium number of documents");
        start = System.currentTimeMillis();
        example = new EssayGroupSet(folder, Directories.getFileNames(folder), 4);
        timeToCreate = (double) (System.currentTimeMillis() - start) / 1000.0;
        example.print();
        System.out.println(String.format("Creation time took: %4.2f seconds", timeToCreate));

        folder = new File("./Large number of documents");
        start = System.currentTimeMillis();
        example = new EssayGroupSet(folder, Directories.getFileNames(folder), 4);
        timeToCreate = (double) (System.currentTimeMillis() - start) / 1000.0;
        example.print(20);
        System.out.println(String.format("Creation time took: %4.2f seconds", timeToCreate));
    }
}
