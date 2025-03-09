import java.util.*;

/**
 * Write a description of class EssayGroup here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EssayGroup
{
    List<EssayPair> list;
    
    public EssayGroup(NumDocs size, List<String> listOfFiles) // List of file paths
    {
        this.list = new ArrayList<EssayPair>();
        for (int i = 0; i < listOfFiles.size(); i++)
        {
            String tempStr = listOfFiles.get(i);
            for (int x = i + 1; x < listOfFiles.size(); x++)
            {
                EssayPair pair = new EssayPair(size, tempStr, listOfFiles.get(x));
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
    
    public static void main(String[] args)
    {
        EssayGroup example = new EssayGroup(NumDocs.SMALL, Directories.getFileNames(NumDocs.SMALL));
        example.print();
        //EssayGroup example = new EssayGroup(NumDocs.MEDIUM, Directories.getFileNames(NumDocs.MEDIUM));
        //example.print();
        //EssayGroup example = new EssayGroup(NumDocs.LARGE, Directories.getFileNames(NumDocs.LARGE));
        //example.print();
    }
}
