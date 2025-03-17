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
        boolean exit = false;
        while (!exit)
        {
            Scanner scanner = new Scanner(System.in);
            startProgram(scanner);

            System.out.println("================="); 
            System.out.println("Thank you for using Catching Plagiarists");
            System.out.print("Enter \"restart\" to restart program or anything other key to exit: ");

            String input = scanner.next().toLowerCase();
            if (!input.equals("restart"))
            {
                exit = true;
            }
            System.out.print('\u000C'); //Clear screen of text
        }
        System.out.print("Exited Catching Plagiarists");
    }

    public static void startProgram(Scanner scanner)
    {
        List<File> dirList = Directories.getDirectories();
        Collections.sort(dirList, Collections.reverseOrder());
        int selected;
        File selectedDir;
        List<String> selectedDirFiles;
        while(true)
        {
            System.out.println("Catching Plagiarists");
            System.out.println("================="); 
            System.out.println("This program is designed to take a directory of files");
            System.out.println("and detect common phrase hits in file pairs. The more");
            System.out.println("hits a file pair has, the higher the likely for the");
            System.out.println("two file pairs are to be plagiarized from each other.");
            System.out.println("================="); 
            System.out.println("Listing directories:");
            for (int i = 0; i < dirList.size(); i++)
            {
                System.out.println((i + 1) + ": " + dirList.get(i).toString());
            }
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

            selectedDir = dirList.get(selected - 1);
            selectedDirFiles = Directories.getFileNames(selectedDir);
            System.out.println("You selected: " + selectedDir.toString());
            int size = selectedDirFiles.size();
            System.out.println("This directory contains " + size + " text files.");
            if (size > 0)
            {
                break;
            }
            System.out.println("Please select different directory to process.");
            try //https://stackoverflow.com/a/24104332
            {
                Thread.sleep(2000);
                System.out.print('\u000C'); //Clear screen of text
            }
            catch (InterruptedException ie)
            {
                Thread.currentThread().interrupt();
            }
        }

        int wordSequence;
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

        int numHits;
        while (true)
        {
            System.out.print("Please enter the threshold of hits you would like list: ");
            try 
            {
                numHits = scanner.nextInt();
                if (numHits >= 0)
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

        System.out.print('\u000C'); //Clear screen of text

        long start = System.currentTimeMillis();
        EssayGroup group = new EssayGroup(selectedDir, selectedDirFiles, wordSequence, numHits);
        double timeToCreate = (double) (System.currentTimeMillis() - start) / 1000.0;
        group.print();
        
        System.out.println(String.format("Creation time took: %4.2f seconds", timeToCreate));
    }
}
