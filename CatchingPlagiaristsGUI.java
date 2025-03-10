import java.util.*;
import java.io.*;

/**
 * This CatchingPlagiarists class represents . . .
 *
 * @author  (your name)
 * @version (todays date)
 */
public class CatchingPlagiaristsGUI
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Catching Plagiarists");
        System.out.println("Listing directories:");

        List<File> dirList = Directories.getDirectories();
        Collections.sort(dirList, Collections.reverseOrder());
        for (int i = 0; i < dirList.size(); i++)
        {
            System.out.println((i + 1) + ": " + dirList.get(i).toString());
        }
        int selected;
        // https://stackoverflow.com/questions/2912817/how-to-use-scanner-to-accept-only-valid-int-as-input
        while (true)
        {
            System.out.print("Please select a directory to process (Type number indicated on the side): ");
            try 
            {
                selected = scanner.nextInt();
                if (selected > 0 && selected <= dirList.size())
                {
                    break;
                }
            } 
            catch (Exception InputMismatchException) 
            {
                scanner.next();
            }
            System.out.println("Invalid Number");
        }
        File selectedDir = dirList.get(selected - 1);
        List<String> selectedDirFiles = Directories.getFileNames(selectedDir);
        int wordSequence;
        System.out.println("You selected: " + selectedDir.toString()); //+ " (" + selected + ")");
        System.out.println("This directory contains " + selectedDirFiles.size() + " text files.");
        while (true)
        {
            System.out.print("Please enter the n-contiguous-word sequences you would like: ");
            try 
            {
                wordSequence = scanner.nextInt();
                if (wordSequence > 0)
                {
                    break;
                }
            } 
            catch (Exception InputMismatchException) 
            {
                scanner.next();
            }
            System.out.println("Not valid number");
        }
    }
}
