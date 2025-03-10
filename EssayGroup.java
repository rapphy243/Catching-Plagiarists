import java.util.*;
import java.io.*;

/**
 * Write a description of class EssayGroup here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EssayGroup
{
    List<EssayPair> list;
    
    public EssayGroup(File folder, List<String> listOfFiles) // List of file paths
    {
        this.list = new ArrayList<EssayPair>();
        for (int i = 0; i < listOfFiles.size(); i++)
        {
            String tempStr = listOfFiles.get(i);
            for (int x = i + 1; x < listOfFiles.size(); x++)
            {
                EssayPair pair = new EssayPair(folder, tempStr, listOfFiles.get(x));
                this.list.add(pair);
            }
        }
        Collections.sort(this.list, Collections.reverseOrder());
    }
    
    public void print()
    {
        System.out.println("=================");
        System.out.println("Printing Examples:");
        for (int i = 0; i < list.size(); i++)
        {
            System.out.print(list.get(i).toString() + " -> ");
            System.out.println(list.get(i).getNumCommonPhrases());
        }
    }
    
    public void print(int results)
    {
        System.out.println("=================");
        System.out.println("Printing Examples:");
        for (int i = 0; i < results; i++)
        {
            System.out.print(list.get(i).toString() + " -> ");
            System.out.println(list.get(i).getNumCommonPhrases());
        }
    }
    
    public static void main(String[] args)
    {
        File folder = new File("./Small number of documents");
        EssayGroup example = new EssayGroup(folder, Directories.getFileNames(folder));
        example.print();
        
        //folder = new File("./Medium number of documents");
        //example = new EssayGroup(folder, Directories.getFileNames(folder));
        //example.print();
        
        //folder = new File("./Large number of documents");
        //example = new EssayGroup(folder, Directories.getFileNames(folder));
        //example.print(20);
    }
}
