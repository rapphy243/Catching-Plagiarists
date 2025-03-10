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
        for (int i = 0; i < listOfFiles.size(); i++)
        {
            tempList.add(new Essay(folder, listOfFiles.get(i), numWords));
        }
        
        for (int i = 0; i < tempList.size(); i++)
        {
            Essay tempEssay = tempList.get(i);
            for (int x = i + 1; x < tempList.size(); x++)
            {
                if (tempEssay.getFileName().equals("jrf1109.shtml.txt") && tempList.get(x).getFileName().equals("sra31.shtml.txt")) 
                {
                    System.out.println();
                }
                EssayPair pair = new EssayPair(tempEssay, tempList.get(x));
                this.list.add(pair);
            }
        }
        
        Collections.sort(this.list, Collections.reverseOrder());
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
    public static void main(String[] args)
    {
        File folder = new File("./Small number of documents");
        EssayGroup example = new EssayGroup(folder, Directories.getFileNames(folder), 4);
        example.print();
        
        //folder = new File("./Medium number of documents");
        //example = new EssayGroup(folder, Directories.getFileNames(folder));
        //example.print();
        
        //folder = new File("./Large number of documents");
        //example = new EssayGroup(folder, Directories.getFileNames(folder), 4);
        //example.print(20);
    }
}
