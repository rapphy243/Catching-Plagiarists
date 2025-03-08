import java.util.*;
import java.io.*;

/**
 * This Directories class represents . . .
 *
 * @author  (your name)
 * @version (todays date)
 */
public class Directories
{
    //https://stackoverflow.com/questions/35006135/initializing-a-dictionary-with-a-specific-set-of-data-cleanly-in-java
    public static final Map<NumDocs, String> directoryMap = new HashMap<NumDocs, String>() {{
                put(NumDocs.LARGE, "./Large number of documents");
                put(NumDocs.MEDIUM, "./Medium number of documents");
                put(NumDocs.SMALL, "./Small number of documents");
            }};

    public static void directoryLister(NumDocs size)
    {
        File dir = new File(directoryMap.get(size));
        String[] temp = dir.list();
        List<String> files = new ArrayList<String>();
        for(int i = 0; i < temp.length; i++)
        {
            if(temp[i].endsWith(".txt"))
                files.add(temp[i]);
        }
        System.out.println(files);
    }
    
    public static List<String> getFileNames(NumDocs size)
    {
        File dir = new File(directoryMap.get(size));
        String[] temp = dir.list();
        List<String> files = new ArrayList<String>();
        for(int i = 0; i < temp.length; i++)
        {
            if(temp[i].endsWith(".txt"))
                files.add(temp[i]);
        }
        return files;
    }
    
    public static List<String> getPathOfFileNames(NumDocs size, List<String> files)
    {
        List<String> filePaths = new ArrayList<String>();
        for(int i = 0; i < files.size(); i++)
        {
            filePaths.add(directoryMap.get(size) + "/" + files.get(i));
        }
        return filePaths;
    }

    public static void main(String[] args)
    {
        System.out.println("The available data sets are "); 
        System.out.println(directoryMap.get(NumDocs.SMALL));
        directoryLister(NumDocs.SMALL);
        // System.out.println(getPathOfFileNames(NumDocs.SMALL, getFileNames(NumDocs.SMALL)));
        System.out.println(directoryMap.get(NumDocs.MEDIUM));
        directoryLister(NumDocs.MEDIUM);
        System.out.println(directoryMap.get(NumDocs.LARGE));
        directoryLister(NumDocs.LARGE);
    }
}
