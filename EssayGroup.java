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
    List<EssayPair> list;
    
    public EssayGroup(File folder, List<String> listOfFiles, int numWords) // List of file paths
    {
        this.list = new ArrayList<EssayPair>();
        
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
                    this.list.add(new EssayPair(tempEssay, tempList.get(x)));
                }
            }
        }
        
        //Collections.sort(this.list, Collections.reverseOrder());
        
        Collections.sort(this.list);
        Collections.reverse(this.list);
    }
    
    public EssayGroup(File folder, List<String> listOfFiles, int numWords, int threshold)
    {
        this.list = new ArrayList<EssayPair>();
        
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
                    this.list.add(new EssayPair(tempEssay, tempList.get(x)));
                }
            }
        }
        
        //Collections.sort(this.list, Collections.reverseOrder());
        
        Collections.sort(this.list);
        //Collections.reverse(this.list);
    }
    
    public void print()
    {
        System.out.println("=================");
        System.out.println("Printing Group:");
        for (int i = 0; i < list.size(); i++)
        {
            System.out.print(list.get(i).toString() + " -> ");
            System.out.println(list.get(i).getCommonPhraseHits());
        }
    }
    
    public void print(int topResults)
    {
        System.out.println("=================");
        System.out.println("Printing Group:");
        for (int i = 0; i < topResults; i++)
        {
            System.out.print(list.get(i).toString() + " -> ");
            System.out.println(list.get(i).getCommonPhraseHits());
        }
    }
    
    public static void progress(int curr, int max) 
    {
        int percent = (int) Math.round(100.0 * curr/ max);
        int numHashes = percent / 2;
        
        String bar = "[";
        for (int i = 0; i < numHashes; i++) 
        {
            bar += "#";
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
