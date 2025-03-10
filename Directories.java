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
    public static List<File> getDirectories()
    {
        File dir = new File(".");
        ArrayList<File> directories = new ArrayList<File>();
        for(File folder : dir.listFiles())
        {
            if(folder.isDirectory())
            {
                directories.add(folder);
            }
        }
        return directories;
    }

    public static List<String> getFileNames(File directory)
    {
        String[] temp = directory.list();
        List<String> files = new ArrayList<String>();
        for(int i = 0; i < temp.length; i++)
        {
            if(temp[i].endsWith(".txt"))
            {
                files.add(temp[i]);
            }
        }
        return files;
    }

    public static void directoryLister(File folder)
    {
        System.out.println(getFileNames(folder));
    }

    public static List<String> getPathOfFileNames(File directory, List<String> files)
    {
        List<String> filePaths = new ArrayList<String>();
        for(int i = 0; i < files.size(); i++)
        {
            filePaths.add(directory + "\\" + files.get(i));
        }
        return filePaths;
    }

    public static void main(String[] args)
    {
        System.out.println("The available data sets are ");
        File dir = new File("./Small number of documents");
        System.out.println(dir);
        directoryLister(dir);
        System.out.println(getPathOfFileNames(dir, getFileNames(dir)));
        
        dir = new File("./Medium number of documents");
        System.out.println(dir);
        directoryLister(dir);
        System.out.println(getPathOfFileNames(dir, getFileNames(dir)));
        
        dir = new File("./Large number of documents");
        System.out.println(dir);
        directoryLister(dir);
        System.out.println(getPathOfFileNames(dir, getFileNames(dir)));
    }
}
