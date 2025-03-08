
/**
 * This Parser class represents . . .
 *
 * @author  (your name)
 * @version (todays date)
 */
public class Parser
{
    public static String parseFile(String path)
    {
        Scanner file = new Scanner(new File(path));
        
        while(file.hasNext())
        {
            String phrase = "";
            // read 'numWords' words from file
            for(int j = 0; j < numWords; j++)
            {
                if(file.hasNext())

                    // strip away all punctuation, and set to lowercase

                    phrase += file.next().replaceAll("[^A-z]","").toLowerCase();
                else
                    phrase = null; // not enough words at end of file
            }
            return phrase;
        }
    }
}
