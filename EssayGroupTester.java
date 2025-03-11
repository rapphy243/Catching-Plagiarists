import java.io.File;

/**
 * This EssayGroupSimulator class represents . . .
 *
 * @author  (your name)
 * @version (todays date)
 */
public class EssayGroupTester
{
    public static void main(String[] args)
    {
        File folder = new File("./Large number of documents");

        long startSet = System.currentTimeMillis();
        EssayGroupSet exampleSet = new EssayGroupSet(folder, Directories.getFileNames(folder), 4);
        exampleSet.print(5);
        double timeToCreateSet = (double) (System.currentTimeMillis() - startSet) / 1000.0;
        
        long start = System.currentTimeMillis();
        EssayGroup example = new EssayGroup(folder, Directories.getFileNames(folder), 4, 1);
        double timeToCreate = (double) (System.currentTimeMillis() - start) / 1000.0;
        example.print(5);
        System.out.println(String.format("Set Creation time took: %4.2f seconds", timeToCreateSet));
        System.out.println(String.format("Array Creation time took: %4.2f seconds", timeToCreate));
        
    }
}

